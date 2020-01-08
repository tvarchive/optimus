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

package com.testvagrant.optimus.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by krishnanand on 20/10/16.
 */
public class DeviceIdentifierTest {

    private String simulatorUDID,deviceUDID;
    @Before
    public void setup() {
        simulatorUDID = "661F45B1-D225-57C8-80C7-F8BA4A613E56";
        deviceUDID = "00008020-00117D9234D1002E";

    }

    @Test
    public void isSimulator() {
        String simulatorRegex = "[a-zA-Z0-9-]{36}";
        assertEquals(simulatorUDID.matches(simulatorRegex),true);
    }

    @Test
    public void isDevice() {
        String deviceRegex = "[a-zA-Z0-9-]{25,40}";
        assertEquals(deviceUDID.matches(deviceRegex),true);
    }
}
