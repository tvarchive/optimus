package com.testvagrant.optimus.utils;

import com.testvagrant.commons.entities.device.OSVersion;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.mdb.utils.OSVersionMatcher;
import org.json.JSONObject;

public class Commons {


    public OSVersion getOSVersion(String osVersion, Platform platform) {
        return new OSVersionMatcher().getOSVersion(platform, osVersion);
    }

    public boolean isUDIDAvailable(JSONObject testFeed) {
        try {
            JSONObject appiumServerCapabilities = (JSONObject) ((JSONObject) testFeed.get("optimusDesiredCapabilities")).get("appiumServerCapabilities");
            String udid = appiumServerCapabilities.getString("udid");
            return udid == null;
        } catch (Exception e) {

        }
        return false;

    }

    public String getUDID(JSONObject testFeed) {
        JSONObject appiumServerCapabilities = (JSONObject) ((JSONObject) testFeed.get("optimusDesiredCapabilities")).get("appiumServerCapabilities");
        String udid = appiumServerCapabilities.getString("udid");
        return udid;
    }

}
