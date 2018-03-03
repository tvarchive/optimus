package com.testvagrant.optimus.device;

import com.testvagrant.monitor.requests.Device;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DeviceAllocation {


    private String Owner;
    private DesiredCapabilities capabilities;
    private Device device;

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
