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

package com.testvagrant.optimus.builder;

import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.commons.entities.reportParser.Step;

import java.util.List;

public class ScenarioBuilder {

    ExecutedScenario scenario = new ExecutedScenario();

    public ExecutedScenario build() {
        return scenario;
    }

    public ScenarioBuilder withDeviceName(String deviceName) {
        scenario.setDeviceName(deviceName);
        return this;
    }

    public ScenarioBuilder withFeatureName(String featureName) {
        scenario.setFeatureName(featureName);
        return this;
    }

    public ScenarioBuilder withId(String id) {
        scenario.setId(id);
        return this;
    }

    public ScenarioBuilder withSteps(List<Step> stepList) {
        scenario.setSteps(stepList);
        return this;
    }

    public ScenarioBuilder withEmbeddedScreen(byte[] screen) {
        scenario.setEmbeddedFailedScreen(screen);
        return this;
    }

    public ScenarioBuilder withScenarioUri(String uri) {
        scenario.setScenarioUri(uri);
        return this;
    }
}
