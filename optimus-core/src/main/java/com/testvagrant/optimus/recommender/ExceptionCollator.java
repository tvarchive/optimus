package com.testvagrant.optimus.recommender;


import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.commons.entities.reportParser.Step;
import com.testvagrant.optimus.entity.ExceptionEntity;
import com.testvagrant.optimus.ml.ExceptionClassifier;
import com.testvagrant.optimus.utils.DataFinder;
import com.testvagrant.optimus.utils.DataStore;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExceptionCollator {

    private List<ExecutedScenario> executedScenarios;

    public ExceptionCollator(List<ExecutedScenario> executedScenarios) {
        this.executedScenarios = executedScenarios;
    }

    public Map<String,List<ExecutedScenario>> collate() {
        DataFinder dataFinder = new DataFinder();
        List<ExecutedScenario> failedScenarios = dataFinder.findFailedScenarios(executedScenarios);
        DataStore dataStore = new DataStore();
        failedScenarios.forEach(failedScenario -> {
            Step failedStep = dataFinder.findFailedStep(failedScenario);
            ExceptionClassifier classifier = new ExceptionClassifier(failedStep.getError_message());
            try {
                ExceptionEntity exceptionEntity = classifier.classifyException();
                dataStore.store(exceptionEntity,failedScenario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return dataStore.getFailedScenarioMap();
    }


}
