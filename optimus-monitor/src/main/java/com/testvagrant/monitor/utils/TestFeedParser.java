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


import com.testvagrant.monitor.entities.TestFeedDetails;
import org.json.simple.JSONObject;

public class TestFeedParser {

    private JSONObject testFeed;

    public TestFeedParser(JSONObject testFeed) {
        this.testFeed = testFeed;
    }

    public TestFeedDetails parse() {
        String runsOn = (String) testFeed.get("runsOn");
        JSONObject optimusDesiredCapabilities = (JSONObject) testFeed.get("optimusDesiredCapabilities");
        JSONObject appiumDesiredCapabilities = (JSONObject) optimusDesiredCapabilities.get("appiumServerCapabilities");
        String udid = (String) appiumDesiredCapabilities.get("udid");
        String platformVersion = (String) appiumDesiredCapabilities.get("platformVersion");
        String platformName = (String) appiumDesiredCapabilities.get("platformName");
        TestFeedDetails testFeedDetails = new TestFeedDetails();
        testFeedDetails.setPlatform(platformName);
        testFeedDetails.setRunsOn(runsOn);
        testFeedDetails.setUdid(udid);
        testFeedDetails.setPlatformVersion(platformVersion);
        return testFeedDetails;
    }

}
