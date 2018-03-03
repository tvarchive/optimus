package com.testvagrant.monitor.services;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.exceptions.DeviceEngagedException;
import com.testvagrant.monitor.exceptions.DeviceReleaseException;
import com.testvagrant.monitor.requests.Device;
import org.json.simple.JSONObject;

import java.util.List;

public interface DevicesService {

    DeviceDetails getDeviceByUdid(String udid);

    List<DeviceDetails> getAllDevices();

    void insertDeviceList(List<DeviceDetails> deviceDetailsList);

    Device updateFirstAvailableDeviceToEngaged(JSONObject testFeed) throws DeviceEngagedException;

    Device updateFirstAvailableDeviceToEngaged(String udid) throws DeviceEngagedException;

    void updateDeviceScreenshot(String udid, byte[] screenshot);

    void updateStatusToAvailableForDevice(String udid) throws DeviceReleaseException;


}
