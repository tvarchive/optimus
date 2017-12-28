package com.testvagrant.mdb.helpers;


import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.entities.device.Status;
import com.testvagrant.mdb.builders.DeviceDetailsBuilder;
import com.testvagrant.mdb.core.CommandExecutor;

import java.util.List;

import static com.testvagrant.mdb.utils.Commands.AndroidCommands.GET_DEVICE_MODEL;
import static com.testvagrant.mdb.utils.Commands.AndroidCommands.GET_DEVICE_OS;

public class AndroidHelper {

    private List<DeviceDetails> deviceDetails;
    private CommandExecutor commandExecutor;

    public AndroidHelper(List<DeviceDetails> deviceDetails) {
        this.deviceDetails = deviceDetails;
        this.commandExecutor = new CommandExecutor();
    }


    public void initEmulators(List<String> processLog) {
        for (String process : processLog) {
            if (isEmulator(process)) {
                String udid = getUID(process);
                String model = getModel(udid);
                String osVersion = getOSVersion(udid);
                DeviceDetails emulator = new DeviceDetailsBuilder()
                        .withDeviceUdid(udid)
                        .withDeviceName(model)
                        .withPlatform(Platform.ANDROID)
                        .withDeviceType(DeviceType.EMULATOR)
                        .withStatus(Status.Available)
                        .withOSVersion(osVersion)
                        .build();
                deviceDetails.add(emulator);
            }
        }
    }

    public void initADevices(List<String> processLog) {
        for (String process : processLog) {
            if (isADevice(process)) {
                String udid = getUID(process);
                String model = getModel(udid);
                String osVersion = getOSVersion(udid);
                DeviceDetails aDevice = new DeviceDetailsBuilder()
                        .withDeviceUdid(udid)
                        .withDeviceName(model)
                        .withPlatform(Platform.ANDROID)
                        .withOSVersion(osVersion)
                        .withDeviceType(DeviceType.DEVICE)
                        .withStatus(Status.Available)
                        .build();
                deviceDetails.add(aDevice);
            }
        }
    }

    private String getUID(String process) {
        String uid = "";
        int uidLastChar = process.indexOf(" ");
        uid = process.substring(0, uidLastChar);
        return uid;
    }

    public String getModel(String UID) {
        String command = String.format(GET_DEVICE_MODEL, UID);
        return commandExecutor.exec(command).asLine().replace("\n", "");
    }

    public String getOSVersion(String UID) {
        String command = String.format(GET_DEVICE_OS, UID);
        return commandExecutor.exec(command).asLine().replace("\n", "");
    }

    private boolean isEmulator(String process) {
        return process.contains("vbox") || process.startsWith("emulator");
    }

    private boolean isADevice(String process) {
        return !process.contains("vbox") && !process.startsWith("emulator") && !process.startsWith("* daemon");
    }
}
