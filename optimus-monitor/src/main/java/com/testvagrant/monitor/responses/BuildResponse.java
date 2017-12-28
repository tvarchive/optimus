package com.testvagrant.monitor.responses;

import com.testvagrant.monitor.requests.Build;
import com.testvagrant.monitor.entities.ErrorResponse;

public class BuildResponse {

    private Build builds;
    private ErrorResponse error;
    private boolean success;

    public Build getBuilds() {
        return builds;
    }

    public void setBuilds(Build builds) {
        this.builds = builds;
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
