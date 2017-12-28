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

    private static final String status = "Available";


    public BasicDBObject prepareQuery(JSONObject testfeed) {

        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();

        obj.add(new BasicDBObject("status", status));

        TestFeedParser testFeedParser = new TestFeedParser(testfeed);
        TestFeedDetails testFeedDetails = testFeedParser.parse();
        obj.add(new BasicDBObject("platform",testFeedDetails.getPlatform().toUpperCase()));
        if (StringUtils.isNoneBlank(testFeedDetails.getUdid())) {
            obj.add(new BasicDBObject("udid", testFeedDetails.getUdid()));
        }
        if (StringUtils.isNoneBlank(testFeedDetails.getPlatformVersion())) {
            obj.add(new BasicDBObject("platformVersion", testFeedDetails.getPlatformVersion()));
        }
        if (!testFeedDetails.getRunsOn().equalsIgnoreCase("any")) {
            obj.add(new BasicDBObject("runsOn", testFeedDetails.getRunsOn().toUpperCase()));
        }
        if (StringUtils.isNoneBlank(testFeedDetails.getDeviceName())) {
            obj.add(new BasicDBObject("deviceName", testFeedDetails.getDeviceName()));
        }


        andQuery.put("$and", obj);

        return andQuery;
    }


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
