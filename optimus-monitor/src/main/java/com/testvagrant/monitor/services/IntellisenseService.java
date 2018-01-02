package com.testvagrant.monitor.services;

import com.testvagrant.commons.entities.reportParser.ExecutedScenario;

import java.util.List;
import java.util.Map;

public interface IntellisenseService {

    void writeFailedScenariosByException(Map<String,List<ExecutedScenario>> failedScenariosMap);
}
