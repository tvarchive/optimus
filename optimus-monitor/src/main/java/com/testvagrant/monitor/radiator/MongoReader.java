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

package com.testvagrant.monitor.radiator;

import com.mongodb.MongoClient;
import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.exceptions.DeviceMatchingException;
import com.testvagrant.monitor.MongoBase;
import com.testvagrant.monitor.clients.DevicesClient;
import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.utils.DeviceToDeviceDetailsMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;

public class MongoReader extends MongoIO   {

    MongoClient mongoClient;
    ObjectMapper objectMapper;

    public MongoReader() {
        mongoClient = MongoBase.getInstance();
        objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
    }

    public List<DeviceDetails> getAllDevices() {
        return new DevicesClient().getAllDevices(latestBuildID);
    }



    public DeviceDetails getDeviceByUdid(String udid) throws DeviceMatchingException {
        Device deviceByUdid = new DevicesClient().getDeviceByUdid(latestBuildID, udid);
        return DeviceToDeviceDetailsMapper.getDeviceDetails(deviceByUdid);
    }


}

