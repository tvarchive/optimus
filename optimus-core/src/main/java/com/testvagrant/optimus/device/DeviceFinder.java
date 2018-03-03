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
import com.testvagrant.commons.exceptions.DeviceEngagedException;
import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.services.DevicesServiceImpl;
import com.testvagrant.optimus.utils.Commons;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class DeviceFinder {
    public Device getAvailableDeviceAndUpdateToEngaged(JSONObject testFeed) throws DeviceEngagedException {
        List<DeviceDetails> deviceDetailsList = new DevicesServiceImpl().getAllDevices();

        System.out.println("-------- All devices and there status --------");

        for (DeviceDetails deviceDetails : deviceDetailsList) {
            System.out.println(deviceDetails.getUdid() + " --- " + deviceDetails.getStatus());
        }
        org.json.simple.JSONObject jsonObject = null;
        try {
            jsonObject = (org.json.simple.JSONObject) new JSONParser().parse(testFeed.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        DeviceDetails deviceDetails = new DeviceMiner(deviceOpDetailsList, testFeed).getAvailableDevice();

        Device deviceDetails = null;

        synchronized (this) {
            if (new Commons().isUDIDAvailable(testFeed)) {
                String udid = new Commons().getUDID(testFeed);
                System.out.println("Finding device in DB with udid - " + udid);
                deviceDetails = new DevicesServiceImpl().updateFirstAvailableDeviceToEngaged(udid);
            } else {
                deviceDetails = new DevicesServiceImpl().updateFirstAvailableDeviceToEngaged(jsonObject);
            }
        }
        return deviceDetails;
    }
}
