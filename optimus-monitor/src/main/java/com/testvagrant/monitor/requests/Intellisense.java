package com.testvagrant.monitor.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Intellisense {

    private String id;
    private String buildId;
    private String exceptionsense;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getExceptionsense() {
        return exceptionsense;
    }

    public void setExceptionsense(String exceptionsense) {
        this.exceptionsense = exceptionsense;
    }
}
