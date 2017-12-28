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

package com.testvagrant.monitor.entities;


public class ScenarioTimeline {

    private int interval;
    private CpuData cpuData;
    private MemoryData memoryData;
    private String activity;
    private String screenshotFileName;
    private byte[] screenshotData;

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public CpuData getCpuData() {
        return cpuData;
    }

    public void setCpuData(CpuData cpuData) {
        this.cpuData = cpuData;
    }

    public MemoryData getMemoryData() {
        return memoryData;
    }

    public void setMemoryData(MemoryData memoryData) {
        this.memoryData = memoryData;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getScreenshotFileName() {
        return screenshotFileName;
    }

    public void setScreenshotFileName(String screenshotFileName) {
        this.screenshotFileName = screenshotFileName;
    }

    public byte[] getScreenshotData() {
        return screenshotData;
    }

    public void setScreenshotData(byte[] screenshotData) {
        this.screenshotData = screenshotData;
    }
}
