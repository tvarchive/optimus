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

public class OptimusDesiredCapabilities {

    private IOSOnlyCapabilities iOSOnlyCapabilities;

    private AppiumServerCapabilities appiumServerCapabilities;

    private AndroidOnlyCapabilities androidOnlyCapabilities;

    public IOSOnlyCapabilities getiOSOnlyCapabilities() {
        return iOSOnlyCapabilities;
    }

    public void setiOSOnlyCapabilities(IOSOnlyCapabilities iOSOnlyCapabilities) {
        this.iOSOnlyCapabilities = iOSOnlyCapabilities;
    }

    public AppiumServerCapabilities getAppiumServerCapabilities() {
        return appiumServerCapabilities;
    }

    public void setAppiumServerCapabilities(AppiumServerCapabilities appiumServerCapabilities) {
        this.appiumServerCapabilities = appiumServerCapabilities;
    }

    public AndroidOnlyCapabilities getAndroidOnlyCapabilities() {
        return androidOnlyCapabilities;
    }

    public void setAndroidOnlyCapabilities(AndroidOnlyCapabilities androidOnlyCapabilities) {
        this.androidOnlyCapabilities = androidOnlyCapabilities;
    }
}
