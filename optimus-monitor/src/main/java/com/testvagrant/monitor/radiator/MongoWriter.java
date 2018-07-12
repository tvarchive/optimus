/*
 * Copyright (c) 2017.  TestVagrant Technologies
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.testvagrant.monitor.radiator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.commons.exceptions.DeviceEngagedException;
import com.testvagrant.commons.helpers.ScenarioHelper;
import com.testvagrant.monitor.clients.BuildsClient;
import com.testvagrant.monitor.clients.DevicesClient;
import com.testvagrant.monitor.clients.ScenariosClient;
import com.testvagrant.monitor.constants.MongoDB;
import com.testvagrant.monitor.entities.CrashData;
import com.testvagrant.monitor.entities.ScenarioTimeline;
import com.testvagrant.monitor.exceptions.DeviceReleaseException;
import com.testvagrant.monitor.reql.DeviceMatcherFunction;
import com.testvagrant.monitor.requests.Build;
import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.requests.Scenario;
import com.testvagrant.monitor.requests.Screenshot;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.testvagrant.monitor.constants.MongoDB.*;
import static com.testvagrant.monitor.utils.DeviceToDeviceDetailsMapper.getDevicesFromDeviceDetails;

public class MongoWriter extends MongoIO {


    public MongoWriter() {
        super();
    }

    public void insertDeviceList(List<DeviceDetails> deviceDetailsList) {
        latestBuildID = getLatestBuild();
        System.out.println("Inserting devices "+deviceDetailsList);
        List<Device> devicesFromDeviceDetails = getDevicesFromDeviceDetails(latestBuildID,deviceDetailsList);
        new DevicesClient().storeDevices(devicesFromDeviceDetails);
    }


    public void updateDeviceScreenshot(String udid, byte[] screenshot) {
        Device device = new Device();
        device.setUdid(udid);
        device.setScreenshot(screenshot);
        new DevicesClient().storeScreenShot(latestBuildID,device);
    }

    public void notifyBOTRegistration(SmartBOT smartBOT) {
        Scenario scenario = new Scenario();
        scenario.setScenarioName(smartBOT.getScenario().getName());
        scenario.setStartTime(new Date());
        scenario.setDeviceUdid(smartBOT.getDeviceUdid());
        scenario.setTags(smartBOT.getScenario().getSourceTagNames());
        scenario.setBuildId(latestBuildID);
        setScenarioLocation(smartBOT, scenario);
        new ScenariosClient().createNewScenario(scenario, smartBOT.getScenario().getLines());
    }

    private void setScenarioLocation(SmartBOT smartBOT, Scenario scenario) {
        if(isScenarioOutline(smartBOT)) {
            scenario.setLocation(smartBOT.getScenario().getLines().get(1));
        } else {
            scenario.setLocation(smartBOT.getScenario().getLines().get(0));
        }


    }

    private boolean isScenarioOutline(SmartBOT smartBOT) {
        return smartBOT.getScenario().getLines().size()>1;
    }

    public void notifyScenarioCompletion(SmartBOT smartBOT) {
        Scenario scenario = new ScenariosClient().findRelevantScenario(latestBuildID,"",smartBOT.getScenario().getName(), getLocation(smartBOT), smartBOT.getDeviceUdid());
        Date endTime = new Date();
        long timeTaken = (endTime.getTime() - scenario.getStartTime().getTime()) / 1000;
        scenario.setStatus(smartBOT.getScenario().getStatus().lowerCaseName());
        scenario.setCompleted(true);
        scenario.setEndTime(new Date());
        scenario.setTimeTaken(Math.toIntExact(timeTaken));
        setScenarioLocation(smartBOT,scenario);
        new ScenariosClient().updateScenario(latestBuildID,scenario);
    }


    private Integer getLocation(SmartBOT smartBOT) {
        if(isScenarioOutline(smartBOT)) {
            return smartBOT.getScenario().getLines().get(1);
        } else {
            return smartBOT.getScenario().getLines().get(0);
        }
    }

    private Object getLatestRecordFor(SmartBOT smartBOT) {

        BasicDBObject andQuery = new BasicDBObject();

        List<BasicDBObject> objects = new ArrayList<>();
        String uniqueScenarioName = new ScenarioHelper(smartBOT.getScenario()).getUniqueScenarioName();
//        objects.add(new BasicDBObject(KEY_SCENARIOS_SCENARIO_NAME, smartBOT.getScenario().getId()));
        objects.add(new BasicDBObject(KEY_SCENARIOS_SCENARIO_NAME, uniqueScenarioName));
        objects.add(new BasicDBObject(KEY_SCENARIOS_DEVICE_UDID, smartBOT.getDeviceUdid()));
        andQuery.put(QUERY_AND, objects);

        Document document = mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_SCENARIOS)
                .find(andQuery)
                .sort(new BasicDBObject(MongoDB.KEY_SCENARIOS_START_TIME, -1)).first();
        return document.get(ID);

    }

    public String getLatestRecordFor(ExecutedScenario scenario) {
        Scenario executedScenario = getScenarioByNameAndLocation(scenario);
        Scenario relevantScenario = new ScenariosClient().findRelevantScenario(latestBuildID, "",executedScenario.getScenarioName(), executedScenario.getLocation(),scenario.getDeviceName());
        return relevantScenario.getId();
    }

    public synchronized DeviceDetails updateFirstAvailableDeviceToEngaged(JSONObject testFeed) throws DeviceEngagedException {
        System.out.println("Updating first available device to Engaged");
        Device matchingDevice = new DeviceMatcherFunction().getDeviceQuery(testFeed);
        Device deviceToEngage = new DevicesClient().getDevice(latestBuildID, matchingDevice);
        deviceToEngage.setStatus("Engaged");
        DeviceDetails deviceDetails = new DevicesClient().updateDevice(deviceToEngage);
        System.out.println(String.format("Updated device %s to engaged", deviceDetails.getDeviceName()));
        return deviceDetails;
    }

    public synchronized DeviceDetails updateFirstAvailableDeviceToEngaged(String udid) throws DeviceEngagedException {
        System.out.println(latestBuildID);
        Device matchingDevice = new Device();
        matchingDevice.setUdid(udid);
        matchingDevice.setStatus("Engaged");
        return new DevicesClient().updateDevice(matchingDevice);
    }


    public void updateStatusToAvailableForDevice(String udid) throws DeviceReleaseException {
//        new DevicesClient().releaseDevice(latestBuildID,udid);
    }

    public void updateExecutionDetailsFor(List<ExecutedScenario> scenarios) {
        for (ExecutedScenario executedScenario : scenarios) {
            Scenario scenario = getScenarioByNameAndLocation(executedScenario);
            scenario.setSteps(new Gson().toJson(executedScenario.getSteps()));
            scenario.setFeatureName(executedScenario.getFeatureName());
            scenario.setFailedOnScreen(executedScenario.getEmbeddedFailedScreen());
            new ScenariosClient().updateScenario(latestBuildID,scenario);
        }
    }

    private Scenario getScenarioByNameAndLocation(ExecutedScenario executedScenario) {
        String scenarioId = executedScenario.getId();
        String location = scenarioId.substring(scenarioId.lastIndexOf("-")+1);
        String scenarioName = scenarioId.substring(0,scenarioId.lastIndexOf("-")).replaceAll("-"," ");
        return new ScenariosClient().findRelevantScenario(latestBuildID,"",scenarioName,Integer.parseInt(location),executedScenario.getDeviceName());
    }

    public void notifyBuildStart() {
        System.out.println("Notifyiing build start");
        new BuildsClient().createNewBuild();
    }

    public void notifyBuildEnd() {
        Build buildToUpdate = new BuildsClient().findBuildById(latestBuildID);
        buildToUpdate.setBuildEndTime(new Date());
        new BuildsClient().updateBuildRecord(buildToUpdate);
    }

    public void updateCrashes(SmartBOT bot, String exceptions, String activity) {
        if (exceptions != null && exceptions.length() > 0) {
            ObjectId latestScenario = (ObjectId) getLatestRecordFor(bot);
            Document queryDocument = new Document(ID, latestScenario);
            Document updateDocument = new Document(KEY_SCENARIO_ID, latestScenario)
                    .append(KEY_CRASHES_STACKTRACE, exceptions)
                    .append(KEY_CRASHES_ACTIVITY, activity);
            mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_SCENARIOS).findOneAndUpdate(queryDocument, updateDocument);
        }
    }

    public void updateBuildWithUniqueScenarios() {
        List<Scenario> distinctScenarios = new ScenariosClient().getDistinctScenarios();
        int numberOfUniqueScenarios = distinctScenarios.size();
        int passedScenariosCount = distinctScenarios.stream().filter(scenario -> scenario.getStatus().equalsIgnoreCase(STATUS_PASSED)).collect(Collectors.toList()).size();
        float pass_percentage = (passedScenariosCount*100.0f)/numberOfUniqueScenarios;
        DecimalFormat df = new DecimalFormat("#.0");
        String passRate = df.format(pass_percentage);
        Build buildById = new BuildsClient().findBuildById(latestBuildID);
        buildById.setScenariosCount(numberOfUniqueScenarios);
        buildById.setScenarioSuccessRate(passRate);
        new BuildsClient().updateBuildRecord(buildById);
    }


    public void updateScenarioTimeLine(SmartBOT smartBOT, List<ScenarioTimeline> scenarioTimelines) {
        ScenariosClient scenariosClient = new ScenariosClient();
        Scenario scenario = getScenario(smartBOT, scenariosClient);
        scenarioTimelines.forEach(scenarioTimeline -> {
            if (scenarioTimeline.getScreenshotData() != null) {
                String fileName = scenario.getId()+"_"+scenarioTimeline.getInterval();
                scenarioTimeline.setScreenshotFileName(fileName);
                Screenshot screenshot = new Screenshot();
                screenshot.setFileName(fileName);
                screenshot.setData(scenarioTimeline.getScreenshotData());
                scenariosClient.loadScreenshot(screenshot);
            }
        });
        scenario.setScenarioTimeline(new GsonBuilder().disableHtmlEscaping().create().toJson(scenarioTimelines));
        new ScenariosClient().updateScenario(latestBuildID,scenario);
    }

    private Scenario getScenario(SmartBOT smartBOT, ScenariosClient scenariosClient) {
        Scenario scenario = null;
        if(isScenarioOutline(smartBOT)) {
          scenario  = scenariosClient.findRelevantScenario(latestBuildID, "",smartBOT.getScenario().getName(), smartBOT.getScenario().getLines().get(1),smartBOT.getDeviceUdid());
        } else {
            scenario  = scenariosClient.findRelevantScenario(latestBuildID, "",smartBOT.getScenario().getName(), smartBOT.getScenario().getLines().get(0),smartBOT.getDeviceUdid());
        }
        return scenario;
    }


    public void createCrashCollection() {
        //Get all unique device udid's
        DistinctIterable<String> deviceUdids = mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_SCENARIOS)
                .distinct("deviceUdid", new Document(KEY_BUILD_ID,getLatestBuild()),String.class);
        List<CrashData> crashDataList = new ArrayList<>();
        //for each udid try to get unique activities
        StreamSupport.stream(deviceUdids.spliterator(),false).forEach(udid -> {
            Map<String,List<String>> stackTraceMap = new HashMap<>();
            CrashData crashData = new CrashData();
            crashData.setUdid(udid);
            DistinctIterable<String> documents = mongoClient.getDatabase(DATABASE_NAME)
                    .getCollection(COLLECTION_SCENARIOS)
                    .distinct("activity", new Document(KEY_SCENARIOS_DEVICE_UDID, udid).append(KEY_BUILD_ID,getLatestBuild()),String.class);
            //For each activity on that build get all stacktraces and point it to the activity
            StreamSupport.stream(documents.spliterator(),false).forEach(activity -> {
                List<String> stackTraceList = new ArrayList<>();
                FindIterable<Document> stacktraces = mongoClient.getDatabase(DATABASE_NAME)
                        .getCollection(COLLECTION_SCENARIOS)
                        .find(new Document(KEY_BUILD_ID, getLatestBuild()).append(KEY_SCENARIOS_DEVICE_UDID, udid).append(KEY_CRASHES_ACTIVITY, activity));
                StreamSupport.stream(stacktraces.spliterator(),false).forEach(stacktrace -> {
                    stackTraceList.add(stacktrace.getString(KEY_CRASHES_STACKTRACE));
                });
                stackTraceMap.put(activity,stackTraceList);
            });
            crashData.setActivityStacktraceMap(stackTraceMap);
            crashDataList.add(crashData);
            Object crashAnalyticsList = new GsonBuilder().disableHtmlEscaping().create().toJson(crashDataList);
            mongoClient.getDatabase(DATABASE_NAME)
                    .getCollection(COLLECTION_BUILDS)
                    .findOneAndUpdate(new Document(ID,getLatestBuild()),new Document("crashlytics",crashAnalyticsList));
        });


    }


}
