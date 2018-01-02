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

package com.testvagrant.optimus.helpers;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.exceptions.OptimusException;
import com.testvagrant.monitor.services.DevicesServiceImpl;
import com.testvagrant.optimus.updater.DeviceMiner;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceHelper {

    private JSONObject testFeedJson;

    public DeviceHelper(String appJson) {
        JSONArray jsonArray = (JSONArray) new JSONObject(appJson).get("testFeed");
        this.testFeedJson = (JSONObject) jsonArray.get(0);
    }


    public List<String> getConnectedDevicesMatchingRunCriteria() throws OptimusException {
        List<String> connectedDevices = new DeviceMiner(new DevicesServiceImpl().getAllDevices(), testFeedJson)
                .getAllDevicesThatMatchTheCriteria().stream()
                .map(DeviceDetails::getUdid)
                .collect(Collectors.toList());
        return connectedDevices;

    }


}
