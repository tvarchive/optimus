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
import com.testvagrant.commons.entities.performance.MemoryStatistics;
import com.testvagrant.mdb.android.Android;

import java.util.List;
import java.util.TimerTask;

public class MemoryMonitor extends TimerTask {

    private int counter;
    private int interval;
    private List<MemoryStatistics> memoryStatisticsList;
    private SmartBOT smartBOT;

    public MemoryMonitor(List<MemoryStatistics> memoryStatistics, SmartBOT smartBOT, int interval) {
        this.memoryStatisticsList = memoryStatistics;
        this.smartBOT = smartBOT;
        this.interval = interval;
    }


    public void run() {
        MemoryStatistics memoryStatistics = new Android().getMemoryInfo(smartBOT);
        memoryStatistics.setInterval(++this.counter * this.interval);
        memoryStatisticsList.add(memoryStatistics);
    }
}
