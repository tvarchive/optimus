package com.testvagrant.monitor.services;

import com.google.gson.GsonBuilder;
import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.monitor.clients.IntellisenseClient;
import com.testvagrant.monitor.requests.Intellisense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntellisenseServiceImpl extends OptimusServiceImpl implements IntellisenseService {

    @Override
    public void writeFailedScenariosByException(Map<String, List<ExecutedScenario>> failedScenariosMap) {
        Map<String,List<String>> failedScenariosObjectMap = new HashMap<>();
        failedScenariosMap.keySet().forEach(key -> {
            List<ExecutedScenario> scenarios = failedScenariosMap.get(key);
            List<String> scenarioIds = new ArrayList<String>();
            scenarios.forEach(scenario -> {
                String latestRecordFor = new ScenariosServiceImpl().getLatestRecordFor(scenario);
                scenarioIds.add(latestRecordFor);
            });
            failedScenariosObjectMap.put(key,scenarioIds);
        });
        String exceptions = new GsonBuilder().disableHtmlEscaping().create().toJson(failedScenariosObjectMap);
        Intellisense intellisense = new Intellisense();
        intellisense.setBuildId(getLatestBuild());
        intellisense.setExceptionsense(exceptions);
        new IntellisenseClient().recordExceptionSense(intellisense);
    }
}
