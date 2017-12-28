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

package com.testvagrant.optimus.entity;

public class TestFeed {

    private String runsOn;

    private OptimusDesiredCapabilities optimusDesiredCapabilities;

    private String belongsTo;

    private String appDir;

    private boolean nativeApp;

    public String getRunsOn() {
        return runsOn;
    }

    public void setRunsOn(String runsOn) {
        this.runsOn = runsOn;
    }

    public OptimusDesiredCapabilities getOptimusDesiredCapabilities() {
        return optimusDesiredCapabilities;
    }

    public void setOptimusDesiredCapabilities(OptimusDesiredCapabilities optimusDesiredCapabilities) {
        this.optimusDesiredCapabilities = optimusDesiredCapabilities;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public String getAppDir() {
        return appDir;
    }

    public void setAppDir(String appDir) {
        this.appDir = appDir;
    }

    public boolean isNativeApp() {
        return nativeApp;
    }

    public void setNativeApp(boolean nativeApp) {
        this.nativeApp = nativeApp;
    }
}
