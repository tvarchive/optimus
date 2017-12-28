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

//import com.testvagrant.commons.entities.DeviceDetails;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.utils.JsonUtil;
import com.testvagrant.mdb.android.Android;
import com.testvagrant.mdb.ios.IOS;
import com.testvagrant.optimus.parser.OptimusConfigParser;
import org.apache.commons.exec.OS;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps all devices to a platform by name and id
 */
public class DeviceMatrix {


    private List<DeviceDetails> deviceDetailsList = new ArrayList<>();
    private static Logger logger = Logger.getLogger(DeviceMatrix.class);

    public DeviceMatrix(String testFeed) {
        init(testFeed);
        logger.info(deviceDetailsList);
    }

    public List<DeviceDetails> getDeviceDetailsList() {
        return deviceDetailsList;
    }

    private void init(String testFeed) {
        OptimusConfigParser configParser = new OptimusConfigParser(new JsonUtil().getAppJson(testFeed));

        if (configParser.isForAndroid())
            deviceDetailsList.addAll(new Android().getDevices());

        if (OS.isFamilyMac()) {
            if (configParser.isForIos())
                deviceDetailsList.addAll(new IOS().getDevices());
        }

    }


}
