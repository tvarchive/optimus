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

package com.testvagrant.optimus.entity;

import com.testvagrant.commons.utils.JsonUtil;
import com.testvagrant.optimus.device.OptimusTestBase;
import com.testvagrant.optimus.parser.OptimusConfigParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestFeedTest extends OptimusTestBase {

    @Test
    public void shouldBeAbleToParseTestFeedWithSauceDetails() throws IOException {
        OptimusConfigParser configParser = new OptimusConfigParser(new JsonUtil().getAppJson("singleApp_Cloud_Sequential_Android.json"));
        Sauce sauce = configParser.getSauceLabsDetails();
        Assert.assertNotNull(sauce.getSauceUserName());
        Assert.assertNotNull(sauce.getSauceAccessKey());
    }


    @Test
    public void shouldBeAbleToParseTestFeedWithoutSauceDetails() throws IOException {
        OptimusConfigParser configParser = new OptimusConfigParser(getAppJson("singleApp_Local_Parallel_Fragmentation_Android.json"));
        Sauce sauce = configParser.getSauceLabsDetails();
        Assert.assertNull(sauce);
    }
}
