
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

package com.testvagrant.optimus.builder;

import com.testvagrant.commons.entities.SmartBOT;
import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SmartBOTBuilder {

    SmartBOT smartBOT = new SmartBOT();

    public SmartBOTBuilder() {
    }


    public SmartBOTBuilder withScenario(Scenario scenario) {
        smartBOT.setScenario(scenario);
        return this;
    }

    public SmartBOTBuilder withBelongsTo(String belongsTo) {
        smartBOT.setBelongsTo(belongsTo);
        return this;
    }

    public SmartBOTBuilder withDeviceUdid(String deviceUdid) {
        smartBOT.setDeviceUdid(deviceUdid);
        return this;
    }

    public SmartBOTBuilder withDeviceId(String deviceId) {
        smartBOT.setDeviceId(deviceId);
        return this;
    }

    public SmartBOTBuilder withDriver(AppiumDriver androidDriver) {
        smartBOT.setDriver(androidDriver);
        return this;
    }

    public SmartBOT build() {
        return smartBOT;
    }

    public SmartBOTBuilder withCapabilities(DesiredCapabilities capabilities) {
        smartBOT.setCapabilities(capabilities);
        return this;
    }


    public SmartBOTBuilder withAppiumService(AppiumDriverLocalService appiumService) {
        smartBOT.setAppiumService(appiumService);
        return this;
    }

    public SmartBOTBuilder withRunsOn(String runsOn) {
        smartBOT.setRunsOn(runsOn);
        return this;
    }

    public SmartBOTBuilder withAppPackageName(String packageName) {
        smartBOT.setAppPackageName(packageName);
        return this;
    }
}
