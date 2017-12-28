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

package com.testvagrant.optimus.helpers;

import com.testvagrant.commons.exceptions.DeviceMatchingException;
import com.testvagrant.commons.exceptions.OptimusException;
import com.testvagrant.optimus.device.OptimusTestBase;
import com.testvagrant.optimus.register.DeviceRegistrar;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DeviceHelperTest extends OptimusTestBase {

    @Test
    public void shouldBeAbleToReadConnectedDevices() throws OptimusException {
        final DeviceRegistrar deviceRegistrar = new DeviceRegistrar();
        deviceRegistrar.setUpDevices(deviceMatrix);

        List<String> udidOfConnectedDevices = new DeviceHelper(getAppJson("singleApp_Local_Sequential_Android_Emulator.json")).getConnectedDevicesMatchingRunCriteria();
        Assert.assertTrue(udidOfConnectedDevices.size() > 0);
        for (String connectedDevice : udidOfConnectedDevices) {
            System.out.println(connectedDevice);
        }
    }
}
