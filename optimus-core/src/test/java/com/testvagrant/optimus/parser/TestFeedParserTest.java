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

package com.testvagrant.optimus.parser;

import com.testvagrant.commons.utils.JsonUtil;
import com.testvagrant.optimus.device.OptimusTestBase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by sukeshkumar on 17/10/16.
 */
public class TestFeedParserTest extends OptimusTestBase {
    @Test
    public void isForAndroid() throws Exception {
        OptimusConfigParser configParser = new OptimusConfigParser(new JsonUtil().getAppJson("singleApp_Local_Sequential_Android.json"));

        Assert.assertTrue(configParser.isForAndroid());
    }

    @Test
    public void foo() throws IOException {
        String appJson = getAppJson("singleApp_Local_Sequential_Android_Minimal_Capabilities.json");
//        System.out.println(appJsonWithRunConfig);


        JSONObject jsonObject = new JSONObject(appJson);

        JSONObject testFeedOne = (JSONObject) (((JSONArray) jsonObject.get("testFeed")).get(0));
        JSONObject androidOnlyCapabilities = (JSONObject) ((JSONObject) testFeedOne.get("optimusDesiredCapabilities")).get("androidOnlyCapabilities");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Iterator<String> keys = androidOnlyCapabilities.keys();
        while (keys.hasNext()) {
            String key = keys.next();

            System.out.println("Key -- " + key);
            Object value = androidOnlyCapabilities.get(key);
            if (value instanceof Boolean) {

//                System.out.println("Value -- " + androidOnlyCapabilities.getBoolean(key));
                desiredCapabilities.setCapability(key, androidOnlyCapabilities.getBoolean(key));
            } else if (value instanceof String) {
//                System.out.println("Value -- " + value.toString());
                desiredCapabilities.setCapability(key, androidOnlyCapabilities.get(key));
            } else if (value instanceof Integer) {
//                System.out.println("Value -- " + androidOnlyCapabilities.getInt(key));
                desiredCapabilities.setCapability(key, androidOnlyCapabilities.getInt(key));
            }

        }

        System.out.println("capabiities created");
//        System.out.println(androidOnlyCapabilities.toString());


    }
}
