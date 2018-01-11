package com.testvagrant.optimus.utils;

import org.json.JSONObject;

public class Commons {


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
