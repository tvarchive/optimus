/*
 * Copyright (c) 2017.  TestVagrant Technologies
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.testvagrant.monitor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoDatabase;
import com.testvagrant.commons.utils.JsonUtil;
import com.testvagrant.monitor.exceptions.MongoInstanceException;
import com.testvagrant.monitor.services.BuildsServiceImpl;

import java.util.ArrayList;

public class MongoMain {

    private String appJson;
    private String runConfig;
    private static String mongoService;

    public MongoMain(String testFeedName) {
        appJson = new JsonUtil().getAppJson(testFeedName + ".json");
        runConfig = getRunConfig();
    }

    private String getRunConfig() {
        JsonObject testFeed = new Gson().fromJson(appJson, JsonObject.class);
        JsonObject executionDetails = testFeed.getAsJsonObject("executionDetails");
        String runConfig;
        try {
            runConfig = executionDetails.get("runConfig").getAsString();
        } catch (Exception e) {
            runConfig = "default";
        }
        return runConfig;
    }

    public void createOptimusDb() throws MongoInstanceException {
        System.out.println("Notifying build start");
        new BuildsServiceImpl().notifyBuildStart();
    }

    public void closeMongo() {
        MongoBase.close();
    }

    private boolean collectionExists(MongoDatabase dbName, String collectionName) {
        return dbName.listCollectionNames()
                .into(new ArrayList<>()).contains(collectionName);
    }

    public static String getMongoService() {
        return mongoService;
    }

    public static void setMongoService(String mongoService) {
        MongoMain.mongoService = mongoService;
    }
}
