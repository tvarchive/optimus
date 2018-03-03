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
 */

package com.testvagrant.commons.entities;

import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SmartBOT {
    private String belongsTo;
    private DesiredCapabilities capabilities;
    private String deviceUdid;
    private AppiumDriverLocalService appiumService;
    private AppiumDriver driver;
    private String runsOn;
    private Scenario scenario;
    private String appPackageName;
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getRunsOn() {
        return runsOn;
    }

    public void setRunsOn(String runsOn) {
        this.runsOn = runsOn;
    }

    public AppiumDriverLocalService getAppiumService() {
        return appiumService;
    }

    public void setAppiumService(AppiumDriverLocalService appiumService) {
        this.appiumService = appiumService;
    }

    public String getDeviceUdid() {
        return deviceUdid;
    }

    public void setDeviceUdid(String deviceUdid) {
        this.deviceUdid = deviceUdid;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public boolean isAnAndroidEmulator() {
        return (deviceUdid.startsWith("emulator") || deviceUdid.contains("vbox"));
    }
}
