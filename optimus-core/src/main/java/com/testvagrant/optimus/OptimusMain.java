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

package com.testvagrant.optimus;

import com.testvagrant.monitor.MongoMain;
import com.testvagrant.monitor.exceptions.MongoInstanceException;
import com.testvagrant.optimus.parser.OptimusConfigParser;
import com.testvagrant.optimus.register.DeviceRegistrar;
import com.testvagrant.optimus.utils.DeviceMatrix;

public class OptimusMain {

    public static void init(String testFeed) throws MongoInstanceException {
        String setupCompleted = System.getProperty("setupCompleted");
        if(setupCompleted==null) {
            setupRunConfiguration(testFeed);
            new MongoMain(testFeed).createOptimusDb();
            new DeviceRegistrar().setUpDevices(new DeviceMatrix(testFeed));
        }
    }

    public static void setupRunConfiguration(String testFeed) {
        String runConfig = new OptimusConfigParser(testFeed).getExecutionDetails().getRunConfig();
    }

    public static void main(String[] args) {
        new DeviceRegistrar().setUpDevices(new DeviceMatrix(args[0]));
        System.exit(0);
    }



}
