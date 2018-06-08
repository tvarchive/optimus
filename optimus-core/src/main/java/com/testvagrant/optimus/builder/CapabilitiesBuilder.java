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

package com.testvagrant.optimus.builder;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.monitor.requests.Device;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Iterator;

public class CapabilitiesBuilder {
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private JSONObject testFeedJSON;

    public CapabilitiesBuilder(JSONObject testFeedJSON, Device device) {

        this.testFeedJSON = testFeedJSON;
        JSONObject appiumServerCapabilities = (JSONObject) ((JSONObject) testFeedJSON.get("optimusDesiredCapabilities")).get("appiumServerCapabilities");
        if (!isNativeApp()) {
            buildWebAppCapabilities(appiumServerCapabilities, device);
            return;
        }
        File app = getAppFile((String) testFeedJSON.get("appDir"), (String) appiumServerCapabilities.get("app"));
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("udid", device.getUdid());
        capabilities.setCapability("deviceName", device.getDeviceName());
        initializeCapabilities();
    }

    private void buildWebAppCapabilities(JSONObject appiumServerCapabilities, Device deviceDetails) {
        if (isBrowserAppProvided(appiumServerCapabilities)) {
            File app = getAppFile((String) testFeedJSON.get("appDir"), (String) appiumServerCapabilities.get("app"));
            capabilities.setCapability("app", app.getAbsolutePath());
        }
        capabilities.setCapability("udid", deviceDetails.getUdid());
        capabilities.setCapability("deviceName", deviceDetails.getDeviceName());
        initializeCapabilities();
    }

    private boolean isAndroid(JSONObject testFeedJSON) {
        String platformName = (String) testFeedJSON.get("platformName");
        return platformName.equalsIgnoreCase("Android");
    }

    private boolean isNativeApp() {
        Boolean nativeApp = testFeedJSON.getBoolean("nativeApp");
        return nativeApp;
    }

    private boolean isBrowserAppProvided(JSONObject testFeedJSON) {
        try {
            String appName = testFeedJSON.getString("app");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void initializeCapabilities() {
        JSONObject appiumServerCapabilities = (JSONObject) ((JSONObject) testFeedJSON.get("optimusDesiredCapabilities")).get("appiumServerCapabilities");
        JSONObject platformSpecificCapabilities = null;

        if (appiumServerCapabilities.get("platformName").toString().equalsIgnoreCase("Android")) {
            platformSpecificCapabilities = (JSONObject) ((JSONObject) testFeedJSON.get("optimusDesiredCapabilities")).get("androidOnlyCapabilities");
        } else if (appiumServerCapabilities.get("platformName").toString().equalsIgnoreCase("iOS")) {
            platformSpecificCapabilities = (JSONObject) ((JSONObject) testFeedJSON.get("optimusDesiredCapabilities")).get("iOSOnlyCapabilities");
        }
        setDesiredCapabilities(appiumServerCapabilities, capabilities);
        setDesiredCapabilities(platformSpecificCapabilities, capabilities);
    }

    private void setDesiredCapabilities(JSONObject platformSpecificCapabilities, DesiredCapabilities desiredCapabilities) {
        Iterator<String> keys = platformSpecificCapabilities.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equalsIgnoreCase("app")) {
                continue;
            }
            Object value = platformSpecificCapabilities.get(key);
            if (value instanceof Boolean) {
                desiredCapabilities.setCapability(key, platformSpecificCapabilities.getBoolean(key));
            } else if (value instanceof String) {
                desiredCapabilities.setCapability(key, platformSpecificCapabilities.get(key));
            } else if (value instanceof Integer) {
                desiredCapabilities.setCapability(key, platformSpecificCapabilities.getInt(key));
            } else if (value instanceof JSONArray) {
                desiredCapabilities.setCapability(key, platformSpecificCapabilities.getJSONArray(key));
            }
        }
    }


    public DesiredCapabilities buildCapabilities() {
        return capabilities;
    }


    private static File getAppFile(String appLocation, String appName) {
        File appDir = new File(appLocation);
        return new File(appDir, appName);
    }

}
