package com.testvagrant.commons.entities;

public class RunConfiguration {
    private String dbname = "optimus";
    private String dburi = "mongodb://localhost:27017";
    private String dbservice  = "http://localhost:8090/v1";

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDburi() {
        return dburi;
    }

    public void setDburi(String dburi) {
        this.dburi = dburi;
    }

    public String getDbservice() {
        return dbservice;
    }

    public void setDbservice(String dbservice) {
        this.dbservice = dbservice;
    }
}
