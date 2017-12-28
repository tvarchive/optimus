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

package com.testvagrant.monitor.radiator;

import com.testvagrant.commons.entities.OptimusConfiguration;

import java.io.File;
import java.io.IOException;

import static com.testvagrant.commons.utils.OptimusConfigMapper.mapper;

public class MongoPort {

    private MongoPort() {

    }

    public static MongoPort mongoPort() {
        return new MongoPort();
    }

    public int getPort() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/META-INF/Optimus.yaml");
        if(file.exists()) {
            OptimusConfiguration configuration = null;
            try {
                configuration = mapper(file).map(OptimusConfiguration.class);
                if(configuration.getMongoPort()!=0) {
                    return configuration.getMongoPort();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 27017;
    }
}
