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

package com.testvagrant.optimus.updater;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.exceptions.OptimusException;
import com.testvagrant.mdb.enums.AOSVersion;
import com.testvagrant.optimus.device.OptimusTestBase;
import com.testvagrant.optimus.register.DeviceRegistrar;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeviceMinerTest extends OptimusTestBase {

    @Test
    public void getAvailableAndroidDeviceTest() throws Exception {
        new DeviceRegistrar().setUpDevices(deviceMatrix);
        DeviceMiner deviceMiner = new DeviceMiner(deviceMatrix.getDeviceDetailsList(),
                (JSONObject) getTestFeedArrayFromAppJson("singleApp_Local_Sequential_Android_Minimal_Capabilities.json").get(0));
        DeviceDetails availableDevice = deviceMiner.getAvailableDevice();
        assertEquals(Platform.ANDROID, availableDevice.getPlatform());
    }

    @Test
    public void getAvailableAndroidDeviceWithSpecifiedUdidTest() throws Exception {
        new DeviceRegistrar().setUpDevices(deviceMatrix);
        DeviceMiner deviceMiner = new DeviceMiner(deviceMatrix.getDeviceDetailsList(),
                (JSONObject) getTestFeedArrayFromAppJson("singleApp_Local_Sequential_Android_Udid.json").get(0));
        DeviceDetails availableDevice = deviceMiner.getAvailableDevice();
        assertEquals(Platform.ANDROID, availableDevice.getPlatform());
        assertEquals("123", availableDevice.getUdid());
    }

    @Test
    public void getAvailableAndroidDeviceWithSpecifiedPlatformVersionTest() throws Exception {
        new DeviceRegistrar().setUpDevices(deviceMatrix);
        DeviceMiner deviceMiner = new DeviceMiner(deviceMatrix.getDeviceDetailsList(),
                (JSONObject) getTestFeedArrayFromAppJson("singleApp_Local_Sequential_Android_PlatformVersion.json").get(0));
        DeviceDetails availableDevice = deviceMiner.getAvailableDevice();
        assertEquals(Platform.ANDROID, availableDevice.getPlatform());
        assertEquals(AOSVersion.LOLLIPOP, availableDevice.getPlatformVersion());
    }

    @Test
    public void getAvailableAndroidDeviceWithSpecifiedDeviceNameTest() throws Exception {
        new DeviceRegistrar().setUpDevices(deviceMatrix);
        DeviceMiner deviceMiner = new DeviceMiner(deviceMatrix.getDeviceDetailsList(),
                (JSONObject) getTestFeedArrayFromAppJson("singleApp_Local_Sequential_Android_DeviceName.json").get(0));
        DeviceDetails availableDevice = deviceMiner.getAvailableDevice();
        assertEquals(Platform.ANDROID, availableDevice.getPlatform());
        assertEquals("Redmi", availableDevice.getDeviceName());
    }

    @Test
    public void getAvailableIOSDeviceTest() throws Exception {
        new DeviceRegistrar().setUpDevices(deviceMatrix);
        DeviceMiner deviceMiner = new DeviceMiner(deviceMatrix.getDeviceDetailsList(),
                (JSONObject) getTestFeedArrayFromAppJson("singleApp_Local_Sequential_iOS.json").get(0));
        DeviceDetails availableDevice = deviceMiner.getAvailableDevice();
        assertEquals(Platform.IOS, availableDevice.getPlatform());
    }

    @Test
    public void getAvailableAndroidDeviceIfNoRunsOnSpecified() throws OptimusException {
        new DeviceRegistrar().setUpDevices(deviceMatrix);
        DeviceMiner deviceMiner = new DeviceMiner(deviceMatrix.getDeviceDetailsList(),
                (JSONObject) getTestFeedArrayFromAppJson("singleApp_Local_Sequential_Android_NoRunsOn.json").get(0));
        DeviceDetails availableDevice = deviceMiner.getAvailableDevice();
        assertEquals(Platform.ANDROID, availableDevice.getPlatform());
    }

    @Test
    public void shouldBeAbleToGetAvailableAndroidDeviceIfBothPlatformVersionAndDeviceNameSpecified() throws OptimusException {
        new DeviceRegistrar().setUpDevices(deviceMatrix);
        DeviceMiner deviceMiner = new DeviceMiner(deviceMatrix.getDeviceDetailsList(),
                (JSONObject) getTestFeedArrayFromAppJson("singleApp_Local_Sequential_Android_DeviceName_PlatformVersion.json").get(0));
        DeviceDetails availableDevice = deviceMiner.getAvailableDevice();
        assertEquals(Platform.ANDROID, availableDevice.getPlatform());
        assertEquals(AOSVersion.MARSHMALLOW, availableDevice.getPlatformVersion());
        assertEquals("Redmi", availableDevice.getDeviceName());
    }


}

