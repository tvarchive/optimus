package com.testvagrant.monitor.services;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.exceptions.DeviceEngagedException;
import com.testvagrant.monitor.clients.DevicesClient;
import com.testvagrant.monitor.exceptions.DeviceReleaseException;
import com.testvagrant.monitor.reql.DeviceMatcherFunction;
import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.utils.DeviceToDeviceDetailsMapper;
import org.json.simple.JSONObject;

import java.util.List;

import static com.testvagrant.monitor.utils.DeviceToDeviceDetailsMapper.getDevicesFromDeviceDetails;

public class DevicesServiceImpl extends OptimusServiceImpl implements DevicesService {
    @Override
    public DeviceDetails getDeviceByUdid(String udid) {
        Device deviceByUdid = new DevicesClient().getDeviceByUdid(getLatestBuild(), udid);
        return DeviceToDeviceDetailsMapper.getDeviceDetails(deviceByUdid);
    }

    @Override
    public List<DeviceDetails> getAllDevices() {
        return new DevicesClient().getAllDevices(getLatestBuild());
    }

    @Override
    public void insertDeviceList(List<DeviceDetails> deviceDetailsList) {
        List<Device> devicesFromDeviceDetails = getDevicesFromDeviceDetails(getLatestBuild(), deviceDetailsList);
        new DevicesClient().storeDevices(devicesFromDeviceDetails);
    }

    @Override
    public DeviceDetails updateFirstAvailableDeviceToEngaged(JSONObject testFeed) throws DeviceEngagedException {
        Device matchingDevice = new DeviceMatcherFunction().getDeviceQuery(testFeed);
        System.out.println("Matching Device is " + matchingDevice.toString());
        Device deviceToEngage = new DevicesClient().getDevice(getLatestBuild(), matchingDevice);
        deviceToEngage.setStatus("Engaged");
        DeviceDetails deviceDetails = new DevicesClient().updateDevice(deviceToEngage);
        return deviceDetails;
    }

    @Override
    public DeviceDetails updateFirstAvailableDeviceToEngaged(String udid) throws DeviceEngagedException {
        Device matchingDevice = new DevicesClient().getDeviceByUdid(getLatestBuild(), udid);
        matchingDevice.setStatus("Engaged");
        return new DevicesClient().updateDevice(matchingDevice);
    }

    @Override
    public void updateDeviceScreenshot(String udid, byte[] screenshot) {
        Device deviceToUpdate = new DevicesClient().getDeviceByUdid(getLatestBuild(), udid);
        deviceToUpdate.setScreenshot(screenshot);
        new DevicesClient().storeScreenShot(getLatestBuild(), deviceToUpdate);
    }

    @Override
    public void updateStatusToAvailableForDevice(String udid) throws DeviceReleaseException {
        Device deviceToUpdate = new DevicesClient().getDeviceByUdid(getLatestBuild(), udid);
        deviceToUpdate.setStatus("Available");
        new DevicesClient().releaseDevice(deviceToUpdate);
    }
}
