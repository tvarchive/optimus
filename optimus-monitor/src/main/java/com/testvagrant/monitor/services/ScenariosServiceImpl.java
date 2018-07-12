package com.testvagrant.monitor.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.monitor.clients.ScenariosClient;
import com.testvagrant.monitor.entities.ScenarioTimeline;
import com.testvagrant.monitor.requests.Scenario;
import com.testvagrant.monitor.requests.Screenshot;
import com.testvagrant.monitor.utils.Commons;

import java.util.Date;
import java.util.List;

public class ScenariosServiceImpl extends OptimusServiceImpl implements ScenariosService {
    @Override
    public void notifyBOTRegistration(SmartBOT smartBOT) {
        Scenario scenario = new Scenario();
        scenario.setScenarioName(smartBOT.getScenario().getName());
        scenario.setStartTime(new Date());
        scenario.setFeatureFileName(new Commons().getFeatureFileName(smartBOT.getScenario().getUri()));
        scenario.setDeviceId(smartBOT.getDeviceId());
        scenario.setTags(smartBOT.getScenario().getSourceTagNames());
        scenario.setBuildId(getLatestBuild());
        scenario.setLocation(getLocation(smartBOT));
        new ScenariosClient().createNewScenario(scenario, smartBOT.getScenario().getLines());
    }

    @Override
    public void notifyScenarioCompletion(SmartBOT smartBOT) {
        Scenario scenario = new ScenariosClient().findRelevantScenario(getLatestBuild(),
                new Commons().getFeatureFileName(smartBOT.getScenario().getUri()),
                smartBOT.getScenario().getName(),
                getLocation(smartBOT),
                smartBOT.getDeviceId());

        Date endTime = new Date();
        long timeTaken = (endTime.getTime() - scenario.getStartTime().getTime()) / 1000;
        scenario.setStatus(smartBOT.getScenario().getStatus().lowerCaseName());
        scenario.setCompleted(true);
        scenario.setEndTime(new Date());
        scenario.setTimeTaken(Math.toIntExact(timeTaken));
        scenario.setLocation(getLocation(smartBOT));
        System.out.println("making scenario update call on completion");
        new ScenariosClient().updateScenario(getLatestBuild(), scenario);
    }

    @Override
    public String getLatestRecordFor(ExecutedScenario scenario) {
        Scenario executedScenario = getScenarioByNameAndLocation(scenario);
        Scenario relevantScenario = new ScenariosClient().findRelevantScenario(getLatestBuild(), new Commons().getFeatureFileName(scenario.getScenarioUri()),executedScenario.getScenarioName(), executedScenario.getLocation(), scenario.getDeviceName());
        return relevantScenario.getId();
    }

    @Override
    public void updateExecutionDetailsFor(List<ExecutedScenario> scenarios) {
        for (ExecutedScenario executedScenario : scenarios) {
            Scenario scenario = getScenarioByNameAndLocation(executedScenario);
            scenario.setSteps(new Gson().toJson(executedScenario.getSteps()));
            scenario.setFeatureName(executedScenario.getFeatureName());
            scenario.setFailedOnScreen(executedScenario.getEmbeddedFailedScreen());
            new ScenariosClient().updateScenario(getLatestBuild(), scenario);
        }
    }

    @Override
    public void updateScenarioTimeLine(SmartBOT smartBOT, List<ScenarioTimeline> scenarioTimelines) {
        ScenariosClient scenariosClient = new ScenariosClient();
        Scenario scenario = getScenario(smartBOT);
        scenarioTimelines.forEach(scenarioTimeline -> {
            if (scenarioTimeline.getScreenshotData() != null) {
                String fileName = scenario.getId() + "_" + scenarioTimeline.getInterval();
                scenarioTimeline.setScreenshotFileName(fileName);
                Screenshot screenshot = new Screenshot();
                screenshot.setFileName(fileName);
                screenshot.setData(scenarioTimeline.getScreenshotData());
                scenariosClient.loadScreenshot(screenshot);
                scenarioTimeline.setScreenshotData(null);
            }
        });
        scenario.setScenarioTimeline(new GsonBuilder().disableHtmlEscaping().create().toJson(scenarioTimelines));
        new ScenariosClient().updateScenario(getLatestBuild(), scenario);

    }

    @Override
    public void updateCrashes(SmartBOT bot, String exceptions, String activity) {
        Scenario scenario = getScenario(bot);
        scenario.setStacktrace(exceptions);
        scenario.setActivity(activity);
        new ScenariosClient().updateScenario(getLatestBuild(), scenario);
    }

    private void setScenarioLocation(SmartBOT smartBOT, Scenario scenario) {
        if (isScenarioOutline(smartBOT)) {
            scenario.setLocation(smartBOT.getScenario().getLines().get(1));
        } else {
            scenario.setLocation(smartBOT.getScenario().getLines().get(0));
        }
    }

    private boolean isScenarioOutline(SmartBOT smartBOT) {
        System.out.println(smartBOT.getScenario().getLines());
        return smartBOT.getScenario().getLines().size() > 1;
    }

    private Integer getLocation(SmartBOT smartBOT) {
        return smartBOT.getScenario().getLines().get(0);
    }

    private Scenario getScenarioByNameAndLocation(ExecutedScenario executedScenario) {
        String scenarioId = executedScenario.getId();
        String location = scenarioId.substring(scenarioId.lastIndexOf("-") + 1);
        String scenarioName = scenarioId.substring(0, scenarioId.lastIndexOf("-"));
        System.out.println("Scenario Name is " + scenarioName);
        System.out.println("Scenario Location is " + location);
        System.out.println("Device Id is " + executedScenario.getDeviceName());
        System.out.println("Latest buildId " + getLatestBuild());
        return new ScenariosClient().findRelevantScenario(getLatestBuild(), new Commons().getFeatureFileName(executedScenario.getScenarioUri()),scenarioName, Integer.parseInt(location), executedScenario.getDeviceName());
    }

    private Scenario getScenario(SmartBOT smartBOT) {
        Scenario scenario = null;
        ScenariosClient scenariosClient = new ScenariosClient();
        scenario = scenariosClient.findRelevantScenario(getLatestBuild(), new Commons().getFeatureFileName(smartBOT.getScenario().getUri()),smartBOT.getScenario().getName(), getLocation(smartBOT), smartBOT.getDeviceId());
        return scenario;
    }


}
