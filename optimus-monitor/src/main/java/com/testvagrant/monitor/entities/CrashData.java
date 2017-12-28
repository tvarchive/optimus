package com.testvagrant.monitor.entities;

import java.util.List;
import java.util.Map;

public class CrashData {

    private String udid;
    private Map<String,List<String>> activityStacktraceMap;

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public Map<String, List<String>> getActivityStacktraceMap() {
        return activityStacktraceMap;
    }

    public void setActivityStacktraceMap(Map<String, List<String>> activityStacktraceMap) {
        this.activityStacktraceMap = activityStacktraceMap;
    }
}
