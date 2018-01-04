package com.testvagrant.monitor.services;

public interface BuildsService {

    void notifyBuildStart();

    void notifyBuildEnd();

    void updateBuildWithUniqueScenarios();

    void createCrashCollection();
}
