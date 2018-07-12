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

package com.testvagrant.commons.entities.reportParser;

import java.util.List;

public class ExecutedScenario {

    private String deviceName;
    private String id;
    private List<Step> steps;
    private byte[] embeddedFailedScreen;
    private String featureName;
    private String scenarioUri;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public byte[] getEmbeddedFailedScreen() {
        return embeddedFailedScreen;
    }

    public void setEmbeddedFailedScreen(byte[] embeddedFailedScreen) {
        this.embeddedFailedScreen = embeddedFailedScreen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getScenarioUri() {
        return scenarioUri;
    }

    public void setScenarioUri(String scenarioUri) {
        this.scenarioUri = scenarioUri;
    }
}
