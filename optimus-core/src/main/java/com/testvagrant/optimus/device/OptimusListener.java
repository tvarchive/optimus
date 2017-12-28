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

package com.testvagrant.optimus.device;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.monitor.performance.ScenarioMonitor;

import java.util.ArrayList;
import java.util.List;

public class OptimusListener {


    private List<SmartBOT> smartBOTs = new ArrayList<>();
    private List<ScenarioMonitor> scenarioMonitors = new ArrayList<>();


    public void setSmartBOTs(List<SmartBOT> smartBOTs) {
        this.smartBOTs = smartBOTs;
    }


    public void start() {
        for (SmartBOT smartBOT : smartBOTs) {
            ScenarioMonitor scenarioMonitor = new ScenarioMonitor(smartBOT);
            scenarioMonitors.add(scenarioMonitor);
            scenarioMonitor.start();
        }

    }

    public void stop() {
        for (ScenarioMonitor monitor : scenarioMonitors) {
            monitor.stop();
        }

    }
}
