package com.testvagrant.monitor.utils;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.entities.device.Status;
import com.testvagrant.monitor.requests.Device;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceToDeviceDetailsMapper {

    public static List<Device> getDevicesFromDeviceDetails(String latestBuildID,List<DeviceDetails> deviceDetailsList) {
        return deviceDetailsList.stream().map(deviceDetails -> getDevice(latestBuildID, deviceDetails)).collect(Collectors.toList());
    }

    public static Device getDevice(String latestBuildID, DeviceDetails deviceDetails) {
        Device device = new Device();
        device.setDeviceName(deviceDetails.getDeviceName());
        device.setRunsOn(deviceDetails.getRunsOn().name());
        device.setPlatform(deviceDetails.getPlatform().getName());
        device.setUdid(deviceDetails.getUdid());
        device.setBuildId(latestBuildID);
        device.setPlatformVersion(deviceDetails.getPlatformVersion());
        return device;
    }


    public static List<DeviceDetails> getDeviceDetailsFromDevices(List<Device> devices) {
        return devices.stream().map(device -> getDeviceDetails(device)).collect(Collectors.toList());
    }

    public static DeviceDetails getDeviceDetails(Device device) {
        DeviceDetails deviceDetails = new DeviceDetails();
        deviceDetails.setRunsOn(DeviceType.valueOf(device.getRunsOn()));
        deviceDetails.setPlatformVersion(device.getPlatformVersion());
        deviceDetails.setDeviceName(device.getDeviceName());
        deviceDetails.setUdid(device.getUdid());
        deviceDetails.setStatus(Status.valueOf(device.getStatus()));
        deviceDetails.setPlatform(Platform.valueOf(device.getPlatform().toUpperCase()));
        return deviceDetails;
    }
}
