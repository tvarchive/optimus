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

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by krishnanand on 26/05/17.
 */
public class ScenarioHelperTest {

    @Test
    public void test() {
        String val = "mandatory-fields-title;verify-title-mandatory-field1";
        Matcher matcher = getMatcher1(val);
        String scenarioNameString = matcher.group(3);
        char c = scenarioNameString.charAt(scenarioNameString.length() - 1);
        boolean digit = Character.isDigit(c);
        String outlineCount = scenarioNameString.split(";;")[1];
        System.out.println(matcher.group(3));
    }

    private Matcher getMatcher1(String stringtoMatch) {
        Pattern p = Pattern.compile("((.*?);)(.*)(;;[0-9+])?");
        Matcher matcher = p.matcher(stringtoMatch);

        matcher.find();
        return matcher;
    }
}
