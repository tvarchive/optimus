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

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.testvagrant.monitor.clients.BuildsClient;
import com.testvagrant.monitor.exceptions.MongoInstanceException;

import java.io.IOException;
import java.util.ArrayList;

import static com.testvagrant.monitor.radiator.MongoDriverService.mongoService;

public class MongoMain {

    private static String mongoMainString;

    public static void main(String[] args) throws MongoInstanceException {
        System.out.println("Getting started with mongo main");
        try {
            mongoService().start();
        } catch (IOException e) {
            throw new MongoInstanceException();
        }
        MongoClient mongoClient = MongoBase.getInstance();
        MongoDatabase optimus = mongoClient.getDatabase("optimus");
        createCollection(optimus, "scenarios");
        createCollection(optimus, "builds");
        createCollection(optimus, "devices");
        createCollection(optimus, "intellisense");
        createCollection(optimus, "testdata");
        System.out.println("Notifying build start");
        new BuildsClient().createNewBuild();
    }

    public static void closeMongo() {
        MongoBase.close();
    }

    private static void createCollection(MongoDatabase dbname, String collectionName) {
        if (!collectionExists(dbname, collectionName)) {
            System.out.println("Created collection " + collectionName);
            dbname.createCollection(collectionName);
        }
    }

    private static boolean collectionExists(MongoDatabase dbName, String collectionName) {
        return dbName.listCollectionNames()
                .into(new ArrayList<>()).contains(collectionName);
    }

}
