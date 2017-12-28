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

package com.testvagrant.monitor.utils;

import com.google.gson.InstanceCreator;
import com.testvagrant.commons.entities.device.OSVersion;
import com.testvagrant.mdb.enums.AOSVersion;

import java.lang.reflect.Type;

@Deprecated
public class OSVersionInstanceCreator implements InstanceCreator<OSVersion> {
    @Override
    public OSVersion createInstance(Type type) {
        AOSVersion version = AOSVersion.MARSHMALLOW;
        OSVersion osVersion = new OSVersion();
        osVersion.setName(version.getName());
        osVersion.setBaseVersion(version.getBaseVersion());
        osVersion.setVersion(version.getVersion());
        return osVersion;
    }
}
