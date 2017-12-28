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

package com.testvagrant.monitor.performance;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.performance.CpuStatistics;
import com.testvagrant.commons.helpers.ScenarioHelper;
import com.testvagrant.mdb.android.Android;

import java.util.List;
import java.util.TimerTask;

public class CPUMonitor extends TimerTask {

    int counter;

    private int interval;
    private SmartBOT smartBOT;
    private List<CpuStatistics> cpuStats;

    public CPUMonitor(List<CpuStatistics> cpuStats, SmartBOT smartBOT, int interval) {
        this.smartBOT = smartBOT;
        this.interval = interval;
        this.cpuStats = cpuStats;
    }

    public void run() {
        CpuStatistics cpuStatistics = new Android().getCpuInfo(smartBOT);
        System.out.println("monitoring cpu for scenario --" + getScenarioName() + "- app" + smartBOT.getAppPackageName());
        cpuStatistics.setInterval((++counter) * interval);
        cpuStats.add(cpuStatistics);
    }

    private String getScenarioName() {
        return new ScenarioHelper(smartBOT.getScenario()).getUniqueScenarioName();
    }
}
