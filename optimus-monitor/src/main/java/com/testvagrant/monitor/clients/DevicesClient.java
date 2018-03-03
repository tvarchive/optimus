package com.testvagrant.monitor.clients;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.exceptions.DeviceEngagedException;
import com.testvagrant.monitor.entities.MongoService;
import com.testvagrant.monitor.exceptions.DeviceReleaseException;
import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.responses.DevicesResponse;
import com.testvagrant.monitor.utils.DeviceToDeviceDetailsMapper;
import io.restassured.response.Response;

import java.util.List;

import static com.testvagrant.monitor.utils.DeviceToDeviceDetailsMapper.getDeviceDetailsFromDevices;
import static io.restassured.RestAssured.given;

public class DevicesClient {

    private final String DEVICES = MongoService.getMongoService() + "/devices";

    public Response storeDevices(List<Device> devices) {
        final Response[] response = new Response[1];
        devices.stream().forEach(device -> {
            device.setStatus("Available");
            response[0] = given()
                    .header("Content-Type", "application/json")
                    .body(device)
                    .post(DEVICES);
        });
        return response[0];
    }


    public List<DeviceDetails> getAllDevices(String buildId) {
        Response response = given()
                .header("Content-Type", "application/json")
                .queryParam("buildId", buildId)
                .get(DEVICES + "/search/findAllByBuildId");
        DevicesResponse devicesResponse = response.as(DevicesResponse.class);
        return getDeviceDetailsFromDevices(devicesResponse.getContent());
    }


    public Device getDevice(String buildId, Device device) {
        Response deviceResponse = given()
                .header("Content-Type", "application/json")
                .queryParam("buildId", buildId)
                .body(device)
                .post(DEVICES + "/findMatchingDevice");
        return deviceResponse.as(Device.class);
    }

    public Response storeScreenShot(String buildId, Device device) {
        Device deviceToUpdate = getDeviceByUdid(buildId, device.getUdid());
        return given()
                .header("Content-Type", "application/json")
                .body(deviceToUpdate)
                .put(DEVICES + String.format("/%s", deviceToUpdate.getId()));
    }


    public DeviceDetails updateDevice(Device device) throws DeviceEngagedException {
        Response engagedDeviceResponse = given()
                .header("Content-Type", "application/json")
                .body(device)
                .put(DEVICES + String.format("/%s", device.getId()));
        System.out.println(engagedDeviceResponse.asString());
        try {
            Device device1 = engagedDeviceResponse.as(Device.class);
            return DeviceToDeviceDetailsMapper.getDeviceDetails(device1);
        } catch (Exception e) {
            throw new DeviceEngagedException();
        }
    }

    public DeviceDetails releaseDevice(Device device) throws DeviceReleaseException {
        Response releaseDeviceResponse = given()
                .header("Content-Type", "application/json")
                .body(device)
                .put(DEVICES + String.format("/%s", device.getId()));
        try {
            Device device1 = releaseDeviceResponse.as(Device.class);
            return DeviceToDeviceDetailsMapper.getDeviceDetails(device1);
        } catch (Exception e) {
            throw new DeviceReleaseException(device.getUdid());
        }
    }


    public Device getDeviceById(String deviceId) {
        Response response = given()
                .header("Content-Type", "application/json")
                .get(DEVICES + String.format("/%s", deviceId));

        return response.as(Device.class);
    }

    public Device getDeviceByUdid(String buildId, String udid) {
        Response deviceResponse = given()
                .header("Content-Type", "application/json")
                .queryParam("buildId", buildId)
                .queryParam("udid", udid)
                .get(DEVICES + "/search/findByBuildIdAndUdid");
        return deviceResponse.as(Device.class);
    }



}
