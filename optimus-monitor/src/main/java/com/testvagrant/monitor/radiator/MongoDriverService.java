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

import com.testvagrant.monitor.MongoBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.testvagrant.monitor.radiator.MongoPort.mongoPort;

public class MongoDriverService {

    private MongoDriverService() {

    }

    public static MongoDriverService mongoService(){
        return new MongoDriverService();
    }

    public void start() throws IOException {
        try {
            MongoBase.getInstance().getAddress();
        } catch (Exception e) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(mongodbCommand());
            processBuilder.start();
        }
    }

    private List<String> mongodbCommand(){
        List<String> strings = new ArrayList<>();
        strings.add("mongod");
        strings.add("--port");
        strings.add(String.valueOf(mongoPort().getPort()));
        return strings;
    }

}
