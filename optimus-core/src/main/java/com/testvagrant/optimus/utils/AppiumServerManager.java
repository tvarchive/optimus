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

package com.testvagrant.optimus.utils;

import com.testvagrant.optimus.device.WDAServerFlag;
import com.testvagrant.optimus.entity.ExecutionDetails;
import com.testvagrant.optimus.parser.OptimusConfigParser;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class AppiumServerManager {


    private ExecutionDetails executionDetails;
    private boolean isAndroid;
    private Optional<Integer> port;

    public AppiumServerManager(OptimusConfigParser configParser) {
        this.executionDetails = configParser.getExecutionDetails();
        this.isAndroid = configParser.isForAndroid();
        port = Optional.empty();
    }


    public AppiumDriverLocalService startAppiumService(String scenarioName, String udid) {
        AppiumDriverLocalService appiumService;
        int wdaPort = aRandomOpenPortOnAllLocalInterfaces();
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(executionDetails.getAppium_node_path()))
                .withAppiumJS(new File(executionDetails.getAppium_js_path()))
                .withIPAddress("127.0.0.1")
                .withArgument(SESSION_OVERRIDE)
                .usingAnyFreePort()
                .withLogFile(new File(String.format("build" + File.separator + "%s.log", scenarioName + "_" + udid)));
        if (isAndroid) {
            appiumServiceBuilder.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, String.valueOf(aRandomOpenPortOnAllLocalInterfaces()));
        } else {
            appiumServiceBuilder.withArgument(WDAServerFlag.WDA_PORT, String.valueOf(wdaPort));
        }
        appiumService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumService.start();
        await().atMost(5, TimeUnit.SECONDS).until(() -> appiumService.isRunning());
        return appiumService;

    }

    public AppiumServerManager withPort(int port) {
        this.port = Optional.of(port);
        return this;
    }


    public Integer aRandomOpenPortOnAllLocalInterfaces() {
        try (
                ServerSocket socket = new ServerSocket(0);
        ) {
            return socket.getLocalPort();

        } catch (IOException e) {
            throw new RuntimeException("no open ports found for bootstrap");
        }
    }
}
