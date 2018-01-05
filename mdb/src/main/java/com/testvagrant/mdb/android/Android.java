package com.testvagrant.mdb.android;


import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.entities.performance.Activity;
import com.testvagrant.commons.entities.performance.CpuStatistics;
import com.testvagrant.commons.entities.performance.Exceptions;
import com.testvagrant.commons.entities.performance.MemoryStatistics;
import com.testvagrant.mdb.Exceptions.ConnectedDevicesException;
import com.testvagrant.mdb.core.Mobile;
import com.testvagrant.mdb.helpers.AndroidHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.testvagrant.mdb.utils.Commands.AndroidCommands.ADB_HEADER;

public class Android extends Mobile implements ADB {

    private List<DeviceDetails> deviceDetails;

    public Android() {
        deviceDetails = new ArrayList<>();
        collectDeviceDetails();
    }

    protected void collectDeviceDetails() {
        List<String> devices = collectDevicesOutput(Platform.ANDROID);
        List<String> collectedDevices = devices.stream().filter(line -> !(line.equals(ADB_HEADER))).collect(Collectors.toList());
        if(collectedDevices.size()==0) {
            throw new ConnectedDevicesException("Could not find any devices, are any devices available?");
        } else {
            AndroidHelper androidHelper = new AndroidHelper(deviceDetails);
            androidHelper.initADevices(collectedDevices);
            androidHelper.initEmulators(collectedDevices);
        }
    }


    public List<DeviceDetails> getDevices() {
        return deviceDetails;
    }

    public List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter) {
        return deviceDetails.stream().filter(deviceFilter).collect(Collectors.toList());
    }

    public List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter, Predicate<DeviceDetails> deviceFilter1) {
        return deviceDetails.stream().filter(deviceFilter).filter(deviceFilter1).collect(Collectors.toList());
    }

    public List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter, Predicate<DeviceDetails> deviceFilter2, Predicate<DeviceDetails> deviceFilter3) {
        return deviceDetails.stream().filter(deviceFilter).filter(deviceFilter2).filter(deviceFilter3).collect(Collectors.toList());
    }


    public MemoryStatistics getMemoryInfo(SmartBOT smartBOT) {
        DumpsysParser dumpsysParser = new DumpsysParser(smartBOT);
        return dumpsysParser.getMemoryInfo();
    }

    public CpuStatistics getCpuInfo(SmartBOT smartBOT) {
        DumpsysParser dumpsysParser = new DumpsysParser(smartBOT);
        return dumpsysParser.getCpuUsage();
    }

    public Activity getActivity(SmartBOT smartBOT) {
        return new DumpsysParser(smartBOT).getCurrentActivity();
    }

    public Optional<Exceptions> getException(SmartBOT smartBOT){
        return new DumpsysParser(smartBOT).getException();
    }
}
