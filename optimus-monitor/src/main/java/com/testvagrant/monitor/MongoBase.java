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

import static com.testvagrant.monitor.radiator.MongoPort.mongoPort;

public class MongoBase {

    private static MongoClient mongoClient = null;

    //Singleton
    private MongoBase() {
    }



    public synchronized static MongoClient getInstance() {
        resetMongoClientIfRequired();
        if(mongoClient==null) {
//            MongoClientURI uri = new MongoClientURI(
//                    "mongodb://krishnanandb:Krishna%400405@firstcluster-shard-00-00-unuus.mongodb.net:27017,firstcluster-shard-00-01-unuus.mongodb.net:27017,firstcluster-shard-00-02-unuus.mongodb.net:27017/optimus?ssl=true&replicaSet=FirstCluster-shard-0&authSource=admin"
//            );
//             mongoClient = new MongoClient(uri);
            mongoClient = new MongoClient(optimusHost(), optimusPort());
        }
        return mongoClient;
    }


    private static String optimusHost() {
        return "localhost";
    }

    private static int optimusPort() {
        return mongoPort().getPort();
    }



    public synchronized static void close(){
        mongoClient.close();
    }


    private static void resetMongoClientIfRequired() {
        if(mongoClient!=null) {
            try {
                mongoClient.getConnectPoint();
            } catch (Exception e) {
                mongoClient = new MongoClient(optimusHost(), optimusPort());
            }
        }
    }

}
