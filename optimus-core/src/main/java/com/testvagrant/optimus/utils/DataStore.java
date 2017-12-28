package com.testvagrant.optimus.utils;


import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.optimus.entity.ExceptionEntity;

import java.util.*;

public class DataStore {
    private List<String> failedScenarios;
    private Map<String,List<ExecutedScenario>> failedScenarioMap;

    public DataStore() {
        failedScenarioMap = new HashMap<>();
        failedScenarios = new ArrayList<>();
    }

    public void store(ExceptionEntity exceptionEntity, ExecutedScenario scenario) {
        failedScenarioMap.put(exceptionEntity.getName(), getUpdatedRecords(failedScenarioMap,exceptionEntity.getName(),scenario));
    }


    private List<ExecutedScenario> getUpdatedRecords(Map<String,List<ExecutedScenario>> key, String exceptionKey, ExecutedScenario scenario) {
        ArrayList<ExecutedScenario> list = new ArrayList<>();
        if(key.containsKey(exceptionKey)) {
            list.addAll(key.get(exceptionKey));
        }
        list.add(scenario);
        return list;
    }

    public Map<String, List<ExecutedScenario>> getFailedScenarioMap() {
        return failedScenarioMap;
    }
}
