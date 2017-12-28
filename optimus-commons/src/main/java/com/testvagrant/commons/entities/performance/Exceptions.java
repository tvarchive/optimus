package com.testvagrant.commons.entities.performance;

/**
 * Created by abhishek on 14/06/17.
 */
public class Exceptions {
    private String stacktrace;
    private String activityName;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
}
