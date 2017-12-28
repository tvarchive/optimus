package com.testvagrant.monitor.requests.builders;

import com.testvagrant.monitor.requests.Build;

import java.util.Date;

public class BuildsRequestBuilder {

    private Build builds = new Build();

    public BuildsRequestBuilder() {
        Date date = new Date();
        builds.setBuildEndTime(date);
        builds.setBuildStartTime(date);
        builds.setScenarioCount(0);
        builds.setScenarioSuccessRate("0.0");
    }

    public BuildsRequestBuilder withBuildStartTime(Date buildStartTime) {
        builds.setBuildStartTime(buildStartTime);
        return this;
    }

    public BuildsRequestBuilder withBuildEndTime(Date buildEndTime) {
        builds.setBuildEndTime(buildEndTime);
        return this;
    }

    public BuildsRequestBuilder withScenarioCount(int count) {
        builds.setScenarioCount(count);
        return this;
    }

    public BuildsRequestBuilder withScenarioSuccessRate(String scenarioSuccessRate) {
        builds.setScenarioSuccessRate(scenarioSuccessRate);
        return this;
    }

    public Build build() {
        return builds;
    }
}

