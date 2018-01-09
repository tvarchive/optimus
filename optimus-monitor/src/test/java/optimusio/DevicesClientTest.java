package optimusio;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.entities.device.Status;
import com.testvagrant.commons.exceptions.DeviceEngagedException;
import com.testvagrant.mdb.builders.DeviceDetailsBuilder;
import com.testvagrant.monitor.clients.BuildsClient;
import com.testvagrant.monitor.clients.DevicesClient;
import com.testvagrant.monitor.requests.Build;
import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.services.DevicesServiceImpl;
import com.testvagrant.monitor.utils.DeviceToDeviceDetailsMapper;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DevicesClientTest {

    private List<Device> getDevices() {
        DeviceDetails motog4 = getDevice("moto g4", "6.0.1", "fajlhglkad");
        DeviceDetails motog5 = getDevice("moto g5", "6.0.2", "afadgad");
        List<DeviceDetails> deviceDetails = new ArrayList<>();
        deviceDetails.add(motog4);
        deviceDetails.add(motog5);
        return DeviceToDeviceDetailsMapper.getDevicesFromDeviceDetails("5a4351e352f868b48ef48688",deviceDetails);
    }

    private DeviceDetails getDevice(String deviceName, String osVersion, String udid) {
        return new DeviceDetailsBuilder()
                .withDeviceName(deviceName)
                .withOSVersion(osVersion)
                .withDeviceUdid(udid)
                .withStatus(Status.Available)
                .withPlatform(Platform.ANDROID)
                .withDeviceType(DeviceType.DEVICE).build();
    }

    @Test
    public void devicesTest() {
        Build newBuild = new BuildsClient().createNewBuild();
        Response response = new DevicesClient().storeDevices(getDevices());
        System.out.println(response.asString());

    }

    @Test(expected = DeviceEngagedException.class)
    public void getEngagedDevice() throws DeviceEngagedException {
        Device device = new Device();
        device.setPlatform("ANDROID");
        device.setUdid("5a4351e352f868b48ef48688");
        DeviceDetails deviceDetails = new DevicesClient().updateDevice(device);
        Assert.assertEquals("5a4351e352f868b48ef48688", deviceDetails.getUdid());
    }


    @Test
    public void updateDevice() throws DeviceEngagedException {
        Device device = new Device();
        device.setStatus("Engaged");
        device.setUdid("ZY223D7XPB");
        new DevicesClient().updateDevice(device);
    }
    @Test
    public void getAllDevices() {

        List<DeviceDetails> allDevices = new DevicesServiceImpl().getAllDevices();
        System.out.println(allDevices);
        List<DeviceDetails> deviceDetails = new DevicesClient().getAllDevices("5a4b5af852f868369e3f6f91");
        deviceDetails.forEach(device -> {
            System.out.println(device.getPlatform());
        });
        System.out.println(deviceDetails);
    }

    @Test
    public void dateTest() {

    }
}

