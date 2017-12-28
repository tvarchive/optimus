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


import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.optimus.device.OptimusTestBase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeviceMatrixTest extends OptimusTestBase {


    @Test
    public void deviceDetailsTest() {
        List<DeviceDetails> deviceDetailsList = deviceMatrix.getDeviceDetailsList();
        assertEquals(6, deviceDetailsList.size());
    }


}
