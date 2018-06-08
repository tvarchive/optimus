package com.testvagrant.monitor.entities;

public class MongoService {

    private static String mongoService = System.getProperty("serviceUrl","http://localhost:8090/v1");

    public static String getMongoService() {
        return mongoService;
    }

    public static void setMongoService(String mongoService) {
        MongoService.mongoService = mongoService;
    }
}
