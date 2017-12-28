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
import com.mongodb.MongoClientURI;
import com.testvagrant.commons.entities.RunConfiguration;

public class MongoBase {

    private static MongoClient mongoClient = null;
    private static RunConfiguration runConfiguration = new RunConfiguration();

    //Singleton
    private MongoBase() {
    }


    public static MongoClient newInstance(RunConfiguration runConfiguration) {
        MongoBase.runConfiguration = runConfiguration;
        return getInstance();
    }

    public synchronized static MongoClient getInstance() {
        resetMongoClientIfRequired();
        if(mongoClient==null) {
            mongoClient = new MongoClient(new MongoClientURI(runConfiguration.getDburi()));
        }
        return mongoClient;
    }



    public synchronized static void close(){
        mongoClient.close();
    }


    private static void resetMongoClientIfRequired() {
        if(mongoClient!=null) {
            try {
                mongoClient.getConnectPoint();
            } catch (Exception e) {
                mongoClient = new MongoClient(new MongoClientURI(runConfiguration.getDburi()));
            }
        }
    }

}
