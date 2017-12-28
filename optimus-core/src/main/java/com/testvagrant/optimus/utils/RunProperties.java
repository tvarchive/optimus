package com.testvagrant.optimus.utils;

public class RunProperties {

    public static boolean isDevMode() {
        String devMode = System.getProperty("devMode");
        return devMode != null && Boolean.parseBoolean(devMode);
    }
}
