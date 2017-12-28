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

package com.testvagrant.monitor.performance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.junit.Test;

/**
 * Created by krishnanand on 19/07/17.
 */
public class MongoURITest {

    @Test
    public void test() {



        MongoClientURI uri = new MongoClientURI(
                "mongodb://krishnanandb:Krishna%400405@firstcluster-shard-00-00-unuus.mongodb.net:27017,firstcluster-shard-00-01-unuus.mongodb.net:27017,firstcluster-shard-00-02-unuus.mongodb.net:27017/<DATABASE>?ssl=true&replicaSet=FirstCluster-shard-0&authSource=admin"
        );
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");
    }

    @Test
    public void runConfigTest() {
        String runConfig = getRunConfig();
        System.out.println(runConfig);
    }

    private String getRunConfig() {
        JsonObject testFeed = new Gson().fromJson("{\"executionDetails\": {\n" +
                "    \"appium_js_path\": \"/usr/local/bin/appium\",\n" +
                "    \"appium_node_path\": \"/usr/local/opt/node@6/bin/node\"\n" +
                "  }}",JsonObject.class);
        JsonObject executionDetails = testFeed.getAsJsonObject("executionDetails");
        String runConfig;
        try {
            runConfig = executionDetails.get("runConfig").getAsString();
        } catch (Exception e) {
            runConfig = "default";
        }
        return runConfig;
    }
}
