/*
 * Copyright (c) 2017.  TestVagrant Technologies
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.testvagrant.monitor.radiator;

import com.google.gson.GsonBuilder;
import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.monitor.clients.IntellisenseClient;
import com.testvagrant.monitor.requests.Intellisense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecommendationWriter extends MongoWriter {

    public void writeFailedScenariosByException(Map<String,List<ExecutedScenario>> failedScenariosMap) {
        latestBuildID = getLatestBuild();
        Map<String,List<String>> failedScenariosObjectMap = new HashMap<>();
        failedScenariosMap.keySet().forEach(key -> {
            List<ExecutedScenario> scenarios = failedScenariosMap.get(key);
            List<String> scenarioIds = new ArrayList<String>();
            scenarios.forEach(scenario -> {
                String latestRecordFor = getLatestRecordFor(scenario);
                scenarioIds.add(latestRecordFor);
            });
            failedScenariosObjectMap.put(key,scenarioIds);
        });
        String exceptions = new GsonBuilder().disableHtmlEscaping().create().toJson(failedScenariosObjectMap);
        Intellisense intellisense = new Intellisense();
        intellisense.setBuildId(latestBuildID);
        intellisense.setExceptionsense(exceptions);
        new IntellisenseClient().recordExceptionSense(intellisense);
    }
}
