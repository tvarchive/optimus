package com.testvagrant.mdb.enums;


public enum  Models {

    MOTOROLA("Moto"),
    NEXUS("Nexus");
    private String modelName;
    Models(String modelName) {
        this.modelName = modelName;
    }


    public String getModelName() {
        return modelName;
    }

}
