package com.testvagrant.monitor.services;

import com.testvagrant.monitor.clients.BuildsClient;
import com.testvagrant.monitor.clients.ScenariosClient;
import com.testvagrant.monitor.requests.Build;
import com.testvagrant.monitor.requests.Scenario;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.testvagrant.monitor.constants.MongoDB.STATUS_PASSED;

public class BuildsServiceImpl extends OptimusServiceImpl implements BuildsService {
    @Override
    public void notifyBuildStart() {
        new BuildsClient().createNewBuild();
    }

    @Override
    public void notifyBuildEnd() {
        Build buildToUpdate = new BuildsClient().findBuildById(getLatestBuild());
        buildToUpdate.setBuildEndTime(new Date());
        new BuildsClient().updateBuildRecord(buildToUpdate);
    }

    @Override
    public void updateBuildWithUniqueScenarios() {
        List<Scenario> distinctScenarios = new ScenariosClient().getDistinctScenarios();
        int numberOfUniqueScenarios = distinctScenarios.size();
        System.out.println("Unique Scenarios "+numberOfUniqueScenarios);
        int passedScenariosCount = distinctScenarios.stream().filter(scenario -> scenario.getStatus().equalsIgnoreCase(STATUS_PASSED)).collect(Collectors.toList()).size();
        float pass_percentage = (passedScenariosCount*100.0f)/numberOfUniqueScenarios;
        DecimalFormat df = new DecimalFormat("#.0");
        String passRate = df.format(pass_percentage);
        Build buildById = new BuildsClient().findBuildById(getLatestBuild());
        buildById.setScenarioCount(numberOfUniqueScenarios);
        buildById.setScenarioSuccessRate(passRate);
        new BuildsClient().updateBuildRecord(buildById);
    }
}
