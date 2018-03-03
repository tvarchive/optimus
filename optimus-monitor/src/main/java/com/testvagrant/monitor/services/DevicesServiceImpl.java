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
    public Device updateFirstAvailableDeviceToEngaged(JSONObject testFeed) throws DeviceEngagedException {
        Device matchingDevice = new DeviceMatcherFunction().getDeviceQuery(testFeed);
        System.out.println("Matching Device is " + matchingDevice.toString());
        return new DevicesClient().getDevice(getLatestBuild(), matchingDevice);
    }


    @Override
    public Device updateFirstAvailableDeviceToEngaged(String udid) throws DeviceEngagedException {
        return new DevicesClient().getDeviceByUdid(getLatestBuild(), udid);
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
