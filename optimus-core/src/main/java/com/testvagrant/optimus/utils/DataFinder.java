package com.testvagrant.optimus.utils;


import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.commons.entities.reportParser.Step;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.testvagrant.optimus.predicates.ScenariosPredicate.whenScenarioHasErrorStep;
import static com.testvagrant.optimus.predicates.ScenariosPredicate.whenStepHasErrorMessage;

public class DataFinder {

    public List<ExecutedScenario> findFailedScenarios(List<ExecutedScenario> executedScenarios) {
        return executedScenarios.stream().filter(whenScenarioHasErrorStep()).collect(Collectors.toList());
    }

    public Step findFailedStep(ExecutedScenario scenario) {
        Optional<Step> first = scenario.getSteps().parallelStream().filter(whenStepHasErrorMessage()).findFirst();
        if(first.isPresent()) {
            return first.get();
        } else {
            throw new RuntimeException("Cannot find a failed step for this scenario");
        }
    }


}
