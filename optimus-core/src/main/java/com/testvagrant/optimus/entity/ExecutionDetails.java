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

public class ExecutionDetails {

    private String appium_js_path = "/usr/local/bin/appium";
    private String appium_node_path = "/usr/local/bin/node";
    private String runConfig = "default";


    public String getAppium_js_path() {
        return appium_js_path;
    }

    public void setAppium_js_path(String appium_js_path) {
        this.appium_js_path = appium_js_path;
    }

    public String getAppium_node_path() {
        return appium_node_path;
    }

    public void setAppium_node_path(String appium_node_path) {
        this.appium_node_path = appium_node_path;
    }

    private Sauce sauce;


    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public String getRunConfig() {
        return runConfig;
    }

    public void setRunConfig(String runConfig) {
        this.runConfig = runConfig;
    }
}
