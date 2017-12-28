package com.testvagrant.mdb.device;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.OSVersion;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.mdb.enums.IOSVersion;
import com.testvagrant.mdb.helpers.IOSHelper;
import com.testvagrant.mdb.utils.OSVersionMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IOSHelperTest extends DeviceDetailsBase {

    @InjectMocks
    List<DeviceDetails> deviceDetailsList = new ArrayList<>();

    @Mock
    IOSHelper iosHelper = Mockito.spy(new IOSHelper(deviceDetailsList));

    @Test
    public void test() {
        iosHelper.initIDevices(iOSProcessLog());
        iosHelper.initSimulators(iOSProcessLog());
        Assert.assertEquals(5, deviceDetailsList.size());
        List<DeviceDetails> physicalDevices = deviceDetailsList.stream().filter(deviceDetails -> deviceDetails.getRunsOn().equals(DeviceType.DEVICE)).collect(Collectors.toList());
        List<DeviceDetails> simulatorDevices = deviceDetailsList.stream().filter(deviceDetails -> deviceDetails.getRunsOn().equals(DeviceType.SIMULATOR)).collect(Collectors.toList());
        Assert.assertEquals(1, physicalDevices.size());
        Assert.assertEquals(4, simulatorDevices.size());
        Assert.assertEquals(physicalDevices.get(0).getPlatformVersion(),"10.0.2");
        simulatorDevices.forEach(simulatorDevice -> {
            Assert.assertEquals(simulatorDevice.getPlatformVersion(), "9.3");
        });
    }


    @Test
    public void iosVersionTest() {
        OSVersion version = new OSVersionMatcher().getOSVersion(Platform.IOS,"10.3.1");
        System.out.println(version.getName());
        Assert.assertEquals(version.getVersion(),"10.3.1");
    }

    private IOSVersion getIOSVersion(String name){
        return IOSVersion.valueOf(name.toUpperCase().replace(" ","_"));
    }


}
