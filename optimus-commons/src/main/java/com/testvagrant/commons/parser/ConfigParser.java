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
 */

package com.testvagrant.commons.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.testvagrant.commons.parser.TestFeedConstants.*;

public class ConfigParser {
    JSONObject jsonObject;

    public ConfigParser(String appJson) {
        jsonObject = new JSONObject(appJson);
    }

    public String getAppBelongingTo(String appConsumer) {
        JSONArray testFeedArray = (JSONArray) jsonObject.get(TEST_FEED);
        for (int testFeedIterator = 0; testFeedIterator < testFeedArray.length(); testFeedIterator++) {
            JSONObject testFeedJSON = (JSONObject) testFeedArray.get(testFeedIterator);
            if (testFeedJSON.getString(BELONGS_TO).equalsIgnoreCase(appConsumer))
                return testFeedJSON.getJSONObject(OPTIMUS_DESIRED_CAPABILITIES).getJSONObject(APPIUM_SERVER_CAPABILITIES).getString(APP);

        }
        throw new RuntimeException("No app found for -- " + appConsumer);
    }
}
