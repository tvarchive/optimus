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

package com.testvagrant.monitor.reql;

import com.mongodb.BasicDBObject;
import com.testvagrant.monitor.entities.TestFeedDetails;
import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.utils.TestFeedParser;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DeviceMatcherFunction {


    public Device getDeviceQuery(JSONObject testfeed) {
        Device device = new Device();
        TestFeedParser testFeedParser = new TestFeedParser(testfeed);
        TestFeedDetails testFeedDetails = testFeedParser.parse();
        device.setPlatform(testFeedDetails.getPlatform().toUpperCase());
        device.setUdid(testFeedDetails.getUdid());
        device.setPlatformVersion(testFeedDetails.getPlatformVersion());
        device.setRunsOn(testFeedDetails.getRunsOn().toUpperCase());
        device.setDeviceName(testFeedDetails.getDeviceName());
        return device;
    }

}
