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

package com.testvagrant.optimus.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.Test;

import java.io.File;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

/**
 * Created by krishnanand on 02/03/17.
 */
public class AppiumServiceTest {

    @Test
    public void appiumServiceTest() {
        AppiumDriverLocalService appiumService;
        appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/bin/appium"))
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .withArgument(SESSION_OVERRIDE)
                .withLogFile(new File(String.format("build/%s.log", "A" + "_" + "xyz"))));
        appiumService.start();
        System.out.println(appiumService.isRunning());
    }
}
