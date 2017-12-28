package com.testvagrant.mdb.ios;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.mdb.Exceptions.ConnectedDevicesException;
import com.testvagrant.mdb.Exceptions.UnsupportedPlatformException;
import com.testvagrant.mdb.core.Mobile;
import com.testvagrant.mdb.helpers.IOSHelper;
import org.apache.commons.lang3.SystemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.testvagrant.mdb.utils.Commands.AndroidCommands.ADB_HEADER;

public class IOS extends Mobile implements IDB {

    private List<DeviceDetails> deviceDetailsList;

    public IOS() {
        if(SystemUtils.IS_OS_MAC) {
            deviceDetailsList = new ArrayList<>();
            collectDeviceDetails();
        } else
            throw new UnsupportedPlatformException("Your OS does not support IOS Commands");

    }

    protected void collectDeviceDetails() {
        List<String> devices = collectDevicesOutput(Platform.IOS);
        List<String> collectedDevices = devices.stream().filter(line -> !(line.equals(ADB_HEADER))).collect(Collectors.toList());
        if(collectedDevices.size()==0) {
            throw new ConnectedDevicesException("Could not find any devices, are any devices available?");
        } else {
            IOSHelper helper = new IOSHelper(deviceDetailsList);
            helper.initSimulators(collectedDevices);
            helper.initIDevices(collectedDevices);
        }
    }

    @Override
    public List<DeviceDetails> getDevices() {
        return deviceDetailsList;
    }

    @Override
    public List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter) {
        return deviceDetailsList.stream().filter(deviceFilter).collect(Collectors.toList());
    }

    @Override
    public List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter, Predicate<DeviceDetails> deviceFilter1) {
        return deviceDetailsList.stream().filter(deviceFilter).filter(deviceFilter1).collect(Collectors.toList());
    }

    @Override
    public List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter, Predicate<DeviceDetails> deviceFilter2, Predicate<DeviceDetails> deviceFilter3) {
        return deviceDetailsList.stream().filter(deviceFilter).filter(deviceFilter2).filter(deviceFilter3).collect(Collectors.toList());
    }




}
