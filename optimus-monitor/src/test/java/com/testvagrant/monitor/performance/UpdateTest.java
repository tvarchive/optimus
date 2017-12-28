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

import com.mongodb.MongoClient;
import com.testvagrant.monitor.MongoBase;
import com.testvagrant.monitor.constants.MongoDB;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;

/**
 * Created by testvagrantserver on 12/05/17.
 */
public class UpdateTest {

    @Test
    public void mongoUpdate() {
        MongoClient mongoClient = MongoBase.getInstance();

        Document queryDocument = new Document().append("_id",new ObjectId("591d6d389f74bc993f1914a6"));
        Document updateDocument = new Document("$set",new Document(MongoDB.KEY_SCENARIOS_TIMELINE, "TestTimeline"));
        mongoClient.getDatabase(MongoDB.DATABASE_NAME).getCollection(MongoDB.COLLECTION_SCENARIOS).findOneAndUpdate(queryDocument,updateDocument);
//        mongoClient.getDatabase("optimus").getCollection("cpuStats").updateOne(filter,newDocument);
        MongoBase.close();

    }
}
