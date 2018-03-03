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

package com.testvagrant.optimus.device;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.exceptions.DeviceEngagedException;
import com.testvagrant.commons.exceptions.DeviceMatchingException;
import com.testvagrant.commons.helpers.ScenarioHelper;
import com.testvagrant.monitor.clients.DevicesClient;
import com.testvagrant.monitor.exceptions.DeviceReleaseException;
import com.testvagrant.monitor.performance.CrashMonitor;
import com.testvagrant.monitor.services.BuildsServiceImpl;
import com.testvagrant.monitor.services.DevicesServiceImpl;
import com.testvagrant.optimus.builder.SmartBOTBuilder;
import com.testvagrant.optimus.parser.OptimusConfigParser;
import com.testvagrant.optimus.utils.AppiumServerManager;
import com.testvagrant.optimus.utils.OnDevice;
import com.testvagrant.optimus.utils.RunProperties;
import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class OptimusController {


    private String appJson;
    private Scenario scenario;
    private OptimusListener listener = new OptimusListener();
    private OptimusConfigParser optimusConfigParser;

    private static Logger logger = Logger.getLogger(OptimusController.class);

    public OptimusController(String appJson, Scenario scenario) {
        this.scenario = scenario;
        validateAPPJson(appJson);
        this.appJson = prepareAppJson(appJson);
        this.optimusConfigParser = new OptimusConfigParser(this.appJson);
    }

    private void validateAPPJson(String appJson) {
        if (!isJsonValid(appJson)) {
            logger.error(scenario.getName() + "---" + "json syntax is incorrect");
            throw new RuntimeException("incorrect testFeed json");
        }
    }

    private boolean isJsonValid(String appJson) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(appJson);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String prepareAppJson(String appJson) {
        return runModeFragmentation() ? updatedAppJsonWithUdid(appJson, System.getProperty("udid")) : appJson;
    }

    private boolean runModeFragmentation() {
        String runMode = System.getProperty("runMode");
        if (runMode != null)
            return runMode.equalsIgnoreCase("Fragmentation");
        else
            return false;
    }


    public String updatedAppJsonWithUdid(String appJson, String udid) {
        JSONObject jsonObject = new JSONObject(appJson);
        JSONArray testFeedArray = (JSONArray) jsonObject.get("testFeed");
        JSONObject testFeed = (JSONObject) testFeedArray.get(0);
        JSONObject appiumServerCapabilities = (JSONObject) ((JSONObject) testFeed.get("optimusDesiredCapabilities")).get("appiumServerCapabilities");
        appiumServerCapabilities.put("udid", udid);

        System.out.println("appJson updated with - " + udid);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }


    public void deRegisterSmartBOTs(List<SmartBOT> smartBOTs) throws DeviceReleaseException {
        stopScenarioListernerIfMonitoring(smartBOTs);
        for (SmartBOT engagedBOT : smartBOTs) {
            try {
                if (!engagedBOT.getRunsOn().equalsIgnoreCase("EMULATOR")) {
                    new CrashMonitor(engagedBOT).captureCrashes();
                }
            } catch (Exception e) {
                //ignoring exception for arrayout of bounds thing
            }
            try {
                scenario.write(engagedBOT.getDeviceUdid());
                logger.info(scenario + "---" + "The following BOT de-registered successfully -- " + engagedBOT.getDeviceUdid());
                engagedBOT.getDriver().quit();
                AppiumDriverLocalService appiumService = null;

                appiumService = engagedBOT.getAppiumService();
                appiumService.stop();
            } catch (Exception e) {
                logger.warn("Appium server didn't stop properly for ", e);
            } finally {
                System.out.println("Trying to make Available - " + engagedBOT.getDeviceUdid());
                new DevicesServiceImpl().updateStatusToAvailableForDevice(engagedBOT.getDeviceUdid());
            }
        }
    }

    private void stopScenarioListernerIfMonitoring(List<SmartBOT> smartBOTs) {
        if (optimusConfigParser.isMonitoring() && optimusConfigParser.isForAndroid()) {
            new Radiator(smartBOTs).notifyScenarioCompletion();
            listener.stop();
        }
    }

    public List<SmartBOT> registerSmartBOTs() throws IOException, InterruptedException, DeviceMatchingException, DeviceEngagedException {
        logger.info("Starting Scenario -- " + scenario.getName());
        List<SmartBOT> smartBOTs = new ArrayList<>();


        List<DeviceAllocation> deviceAllocations = optimusConfigParser.allocateDevicesForCurrentScenario();

        for (DeviceAllocation allocatedDevice : deviceAllocations) {
            String udid = allocatedDevice.getDevice().getUdid();

            String uniqueScenarioName = new ScenarioHelper(scenario).getUniqueScenarioName();
            AppiumDriverLocalService appiumService = null;
            AppiumDriver driver = null;
            AppiumServerManager appiumServerManager = new AppiumServerManager(optimusConfigParser);
            if (platformIOS(allocatedDevice.getCapabilities())) {
                appiumService = appiumServerManager
                        .startAppiumService(uniqueScenarioName, udid);
                driver = addDriver(appiumService.getUrl(), allocatedDevice.getCapabilities());
            } else {
                appiumService = new AppiumServerManager(optimusConfigParser)
                        .startAppiumService(uniqueScenarioName, udid);
                driver = addDriver(appiumService.getUrl(), allocatedDevice.getCapabilities());
            }
            logger.info(uniqueScenarioName + "--" + "driver instance created for " + udid);

            String appPackage = (String) allocatedDevice.getCapabilities().getCapability("appPackage");

            SmartBOT bot = new SmartBOTBuilder()
                    .withBelongsTo(allocatedDevice.getOwner())
                    .withRunsOn(getRunsOnBasedOn(udid))
                    .withCapabilities(allocatedDevice.getCapabilities())
                    .withDeviceUdid(udid)
                    .withDeviceId(allocatedDevice.getDevice().getId())
                    .withDriver(driver)
                    .withAppiumService(appiumService)
                    .withScenario(scenario)
                    .withAppPackageName(appPackage)
                    .build();

            byte[] deviceScreenshot = driver.getScreenshotAs(OutputType.BYTES);
            new DevicesServiceImpl().updateDeviceScreenshot(udid, deviceScreenshot);
            new BuildsServiceImpl().updateBuildRunMode(System.getProperty("runMode"));
            smartBOTs.add(bot);

            new OnDevice(bot).clearADBLogs();

            logger.info(scenario + "---" + "BOT registered successfully for -- " + udid);
        }

        if (optimusConfigParser.isMonitoring() && optimusConfigParser.isForAndroid()) {
            new Radiator(smartBOTs).notifyScenarioStart();
            listener.setSmartBOTs(smartBOTs);
            listener.start();
        }

        for (SmartBOT smartBOT : smartBOTs) {
            scenario.write(smartBOT.getDeviceUdid());
        }

        return smartBOTs;
    }


    private String getRunsOnBasedOn(String udid) throws DeviceMatchingException {
        System.out.println("UDID: " + udid);
        DeviceDetails deviceByUdid = new DevicesServiceImpl().getDeviceByUdid(udid);
        System.out.println("DeviceName" + deviceByUdid.getDeviceName());
        return deviceByUdid.getRunsOn().name();

    }

    private boolean platformIOS(DesiredCapabilities desiredCapabilities) {
        String platformName = (String) desiredCapabilities.getCapability("platformName");
        return platformName.equalsIgnoreCase("iOS");
    }

    private Process executeCommand(String command) {
        Runtime rt = Runtime.getRuntime();
        try {
            return rt.exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AppiumDriver addDriver(URL url, DesiredCapabilities capabilities) {

        try {
            return setUpDevice(url, capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Driver could not be created for -- " + capabilities.getCapability("udid"));
    }

    private static AppiumDriver setUpDevice(URL url, DesiredCapabilities capabilities) throws MalformedURLException {
        if (RunProperties.isDevMode()) {
            capabilities.setCapability("noReset", true);
        }
        if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
            return new AndroidDriver(url, capabilities);
        }
        return new IOSDriver(url, capabilities);
    }


}
