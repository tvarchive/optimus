package com.testvagrant.mdb.core;


import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.mdb.utils.Commands;

import java.util.List;

public abstract class Mobile {

    protected CommandExecutor commandExecutor;

    protected Mobile() {
        commandExecutor = new CommandExecutor();
    }

    public List<String> collectDevicesOutput(Platform platform) {
        String command = null;
        switch (platform) {
            case ANDROID:
                command = Commands.AndroidCommands.LIST_ALL_DEVICES;
                break;
            case IOS:
                command = Commands.Instruments.LIST_ALL_DEVICES;
                break;
        }
        List<String> devices = new CommandExecutor().exec(command).asList();
        return devices;
    }

    protected abstract void collectDeviceDetails();


}
