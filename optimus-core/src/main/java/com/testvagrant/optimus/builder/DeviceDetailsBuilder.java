
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

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.entities.device.Status;


public class DeviceDetailsBuilder {
    private DeviceDetails deviceDetails = new DeviceDetails();

    public DeviceDetailsBuilder withUDID(String udid) {
        deviceDetails.setUdid(udid);
        return this;
    }

    public DeviceDetailsBuilder() {
        deviceDetails.setStatus(Status.Available);
        deviceDetails.setPlatform(Platform.ANDROID);
        deviceDetails.setRunsOn(DeviceType.EMULATOR);
    }

    public DeviceDetails build() {
        return deviceDetails;
    }


    public DeviceDetailsBuilder withStatus(Status status) {
        deviceDetails.setStatus(status);
        return this;
    }

    public DeviceDetailsBuilder withPlatformVersion(String platformVersion) {
        deviceDetails.setPlatformVersion(platformVersion);
        return this;
    }

    public DeviceDetailsBuilder withDeviceName(String deviceName) {
        deviceDetails.setDeviceName(deviceName);
        return this;
    }


    public DeviceDetailsBuilder withPlatform(Platform platform) {
        deviceDetails.setPlatform(platform);
        return this;
    }

    public DeviceDetailsBuilder withRunsOn(DeviceType runsOn) {
        deviceDetails.setRunsOn(runsOn);
        return this;
    }
}
