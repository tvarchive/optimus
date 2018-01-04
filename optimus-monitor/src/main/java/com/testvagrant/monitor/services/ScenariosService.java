package com.testvagrant.monitor.services;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.monitor.entities.ScenarioTimeline;

import java.util.List;

public interface ScenariosService {

    void notifyBOTRegistration(SmartBOT smartBOT);

    void notifyScenarioCompletion(SmartBOT smartBOT);

    String getLatestRecordFor(ExecutedScenario scenario);

    void updateExecutionDetailsFor(List<ExecutedScenario> scenarios);

    void updateScenarioTimeLine(SmartBOT smartBOT, List<ScenarioTimeline> scenarioTimelines);

    void updateCrashes(SmartBOT bot, String exceptions, String activity);
}
