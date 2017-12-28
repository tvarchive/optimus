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

package com.testvagrant.commons.entities;


import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.entities.device.Status;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceDetails {

    private String deviceName;
    private Platform platform;
    private String platformVersion;
    private DeviceType runsOn;
    private Status status;
    private String udid;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public DeviceType getRunsOn() {
        return runsOn;
    }

    public void setRunsOn(DeviceType runsOn) {
        this.runsOn = runsOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    @Override
    public String toString() {
        return "DeviceDetails{" +
                "deviceName='" + deviceName + '\'' +
                ", udid='" + udid + '\'' +
                ", status='" + status + '\'' +
                ", platform=" + platform +
                ", platformVersion='" + platformVersion + '\'' +
                ", runsOn=" + runsOn.name() +
                '}';
    }
}
