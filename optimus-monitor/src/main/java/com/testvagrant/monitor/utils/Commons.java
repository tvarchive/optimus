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

package com.testvagrant.monitor.utils;


import com.testvagrant.commons.entities.SmartBOT;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Commons {

    public static Double convertToMB(String dataInKB) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        try {
            Integer data = NumberUtils.toInt(dataInKB);
            double dataInDouble = data/1024.0;
            return dataInDouble;
        } catch (Exception  e) {

        }
        throw new RuntimeException("Unable to convert "+dataInKB+" to MB");
    }

    public static boolean isRegression() {
        try {
            String isRegression = System.getProperty("regression");
            return isRegression==null?true:Boolean.valueOf(isRegression);
        } catch (Exception e) {
            return true;
        }

    }

    public void createTempFolder(SmartBOT smartBOT) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(String.format("adb -s %s shell mkdir /sdcard/%s",smartBOT.getDeviceUdid(),smartBOT.getScenario().getId().replaceAll("[^a-zA-Z0-9]","")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTempFolder(SmartBOT smartBOT) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(String.format("adb -s %s shell rm -rf /sdcard/%s",smartBOT.getDeviceUdid(),smartBOT.getScenario().getId().replaceAll("[^a-zA-Z0-9]","")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getFeatureFileName(String scenarioUri) {
        String featureFileName = "";
        try {
             featureFileName = scenarioUri.substring(scenarioUri.lastIndexOf(File.separator) + 1, scenarioUri.indexOf(".feature"));
            System.out.println(featureFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return "dummyfeaturefile";
        }
        return featureFileName;
    }

}
