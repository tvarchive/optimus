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
 */

package com.testvagrant.commons.helpers;

import cucumber.api.Scenario;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

public class ScenarioHelper {

    private Scenario scenario;
    private Matcher matcher;

    public ScenarioHelper(Scenario scenario) {
        this.scenario = scenario;
//        matcher = getMatcher();
    }

    public String getUniqueScenarioName() {
        System.out.println("Scenario id -- " + scenario.getId());
        System.out.println("Scenario Name -- " + scenario.getName());
//        return scenario.getName();
        checkNotNull(scenario);
        String scenarioNameString = getScenarioName() + "-" + getLineNumber();
        return scenarioNameString;
    }

    @Deprecated
    private Matcher getMatcher() {
        Pattern p = Pattern.compile("((.*?);)(.*)(;;[0-9+])?");
        String id = scenario.getId();
        String[] split = id.split(":");
        String featureName = FilenameUtils.getBaseName(split[0]);
        String lineNumber = split[1];
        String name = scenario.getName();
        String input = featureName + ";" + name + ";;" + lineNumber;
        Matcher matcher = p.matcher(input);

        System.out.println("unique scenario name -- " + input);
        matcher.find();
        return matcher;
    }

    //Getting line number from the uri string with changes according to cucumber 2.0.1
    public String getLineNumber() {
        String id = scenario.getId();
        if (id.contains(":")) {
            if (SystemUtils.IS_OS_WINDOWS) {
                int colonIndex = id.lastIndexOf(":");
                return id.substring(colonIndex + 1, id.length());
            } else {
                String[] uriArray = id.split(":");
                if (uriArray.length > 1)
                    return uriArray[1];
            }
        }
        throw new RuntimeException("Cannot extract line number from the String." + scenario.getId());
    }

    private String getScenarioName() {
        return scenario.getName().replaceAll(" ", "-");
    }

    public String getParentFeatureName() {
        return matcher.group(2);
    }
}
