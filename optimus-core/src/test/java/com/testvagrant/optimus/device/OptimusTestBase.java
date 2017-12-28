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

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.OSVersion;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.mdb.enums.Version;
import com.testvagrant.optimus.builder.DeviceDetailsBuilder;
import com.testvagrant.optimus.utils.DeviceMatrix;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class OptimusTestBase {


    protected DeviceMatrix deviceMatrix;

    protected String getAppJson(String name) {
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Before
    public void initializeDeviceMatrix() {
        deviceMatrix = Mockito.mock(DeviceMatrix.class);
        List<DeviceDetails> mockedDevices = getMockedDevices();

        when(deviceMatrix.getDeviceDetailsList()).thenReturn(mockedDevices);
    }

    protected List<DeviceDetails> getMockedDevices() {
        List<DeviceDetails> mockedDevices = new ArrayList<>();
        DeviceDetails androidEmulatorOne = new DeviceDetailsBuilder().withUDID("123").withPlatform(Platform.ANDROID)
                .withRunsOn(DeviceType.EMULATOR)
                .withDeviceName("Samsung Galaxy")
                .withPlatformVersion("5.0").build();
        DeviceDetails androidEmulatorTwo = new DeviceDetailsBuilder().withUDID("789").withPlatform(Platform.ANDROID)
                .withRunsOn(DeviceType.EMULATOR)
                .withDeviceName("Redmi")
                .withPlatformVersion("6.0").build();
        DeviceDetails androidDeviceOne = new DeviceDetailsBuilder().withUDID("234").withPlatform(Platform.ANDROID)
                .withRunsOn(DeviceType.DEVICE).withDeviceName("Android")
                .withPlatformVersion("7.0").build();
        DeviceDetails androidDeviceTwo = new DeviceDetailsBuilder().withUDID("456").withPlatform(Platform.ANDROID)
                .withRunsOn(DeviceType.DEVICE)
                .withDeviceName("Motorola")
                .withPlatformVersion("7.0").build();
        DeviceDetails iosSimulator = new DeviceDetailsBuilder().withUDID("1234-1234-1234").withPlatform(Platform.IOS)
                .withRunsOn(DeviceType.SIMULATOR).withDeviceName("iPhone 6")
                .withPlatformVersion("9.3").build();
        DeviceDetails iosDevice = new DeviceDetailsBuilder().withUDID("1234-1234-1234").withPlatform(Platform.IOS)
                .withRunsOn(DeviceType.DEVICE).withDeviceName("iPhone 6")
                .withPlatformVersion("10.0").build();

        mockedDevices.addAll(Arrays.asList(androidEmulatorOne, androidEmulatorTwo));
        mockedDevices.addAll(Arrays.asList(androidDeviceOne, androidDeviceTwo));
        mockedDevices.add(iosSimulator);
        mockedDevices.add(iosDevice);
        return mockedDevices;
    }

    protected JSONArray getTestFeedArrayFromAppJson(String inputJson) {
        String appJson = getAppJson(inputJson);
        JSONObject jsonObject = new JSONObject(appJson);
        return (JSONArray) jsonObject.get("testFeed");
    }

    protected int getNumberOfDevicesRequiredFromAppJson(String inputJson) {
        String appJson = getAppJson(inputJson);
        JSONObject jsonObject = new JSONObject(appJson);
        JSONObject executionDetails = (JSONObject) jsonObject.get("executionDetails");
        return (int) executionDetails.get(("numberOfDevices"));
    }


    private OSVersion getOSVersion(Version version, String versionNumber) {
        OSVersion osVersion = new OSVersion();
        osVersion.setName(version.getName());
        osVersion.setBaseVersion(version.getBaseVersion());
        osVersion.setVersion(versionNumber);
        return osVersion;
    }
}
