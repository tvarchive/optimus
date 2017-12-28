package com.testvagrant.monitor.responses;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.monitor.entities.ErrorResponse;

public class DeviceResponse {

    private DeviceDetails devices;
    private ErrorResponse error;
    private boolean success;

    public DeviceDetails getDevices() {
        return devices;
    }

    public void setDevices(DeviceDetails devices) {
        this.devices = devices;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
