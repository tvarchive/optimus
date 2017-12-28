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


import com.testvagrant.commons.entities.SmartBOT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ADBUtil {

    private SmartBOT smartBOT;

    public ADBUtil(SmartBOT smartBOT) {
        this.smartBOT = smartBOT;
    }

    public StringBuilder readAdbLog(String cmd) {
        String command = cmd;
        String line;
        StringBuilder log = new StringBuilder();
        Process process;
        Runtime rt = Runtime.getRuntime();
        try {
            process = rt.exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    process.getErrorStream()));


            while ((line = stdInput.readLine()) != null) {
                log.append(line);
                log.append(System.getProperty("line.separator"));
            }
            while ((line = stdError.readLine()) != null) {
                log.append(line);
                log.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log;
    }

    public void grantPermission(String permissionName) throws InterruptedException, IOException {
        Runtime rt = Runtime.getRuntime();

        Process exec = rt.exec(String.format("adb -s %s shell pm grant %s %s", smartBOT.getDeviceUdid()
                , smartBOT.getCapabilities().getCapability("appPackage"), permissionName));
        while (exec.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
