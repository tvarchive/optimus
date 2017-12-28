package com.testvagrant.monitor.responses;

import com.testvagrant.monitor.requests.Scenario;

import java.util.List;

public class ScenariosResponse {

    private List<Scenario> scenarios;

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }
}
