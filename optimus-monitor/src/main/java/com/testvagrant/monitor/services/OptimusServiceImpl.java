package com.testvagrant.monitor.services;

import com.testvagrant.monitor.clients.BuildsClient;

public class OptimusServiceImpl implements OptimusService {
    private static String latestBuildID;

    @Override
    public String getLatestBuild() {
        return new BuildsClient().getLatestBuildId();
    }
}
