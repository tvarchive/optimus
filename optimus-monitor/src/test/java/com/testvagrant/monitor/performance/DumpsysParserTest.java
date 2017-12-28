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

import com.testvagrant.commons.entities.SmartBOT;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;


public class DumpsysParserTest {

    @Test
    public void testRegexForCpuUsageOutput() {
        String check1 = "0.5% 726/system_server: 22% user + 5.6% kernel";
        String check2 = "0.5% 726/system_server: 2.5% user + 15% kernel";
        String check3 = "0.5% 726/system_server: 2.5% user + 1.5% kernel";
        String check4 = "0.5% 726/system_server: 22.5% user + 1.58% kernel";
//        String check5 ="+0% 6008/com.testvagrant.hellooptimus: 0% user + 0% kernel";
        String check5 ="3.1% TOTAL: 2.2% user + 0.7% kernel + 0% iowait + 0% softirq";

        Pattern p = Pattern.compile("([0-9]+(\\.[0-9]+)?%)\\s+(user)\\s+(\\+)\\s+([0-9]+(\\.[0-9]+)?%)\\s+(kernel)(.*)");
        Matcher matcher1 = p.matcher(check1);
        Matcher matcher2 = p.matcher(check2);
        Matcher matcher3 = p.matcher(check3);
        Matcher matcher4 = p.matcher(check4);
        Matcher matcher5 = p.matcher(check5);

        matcher1.find();
        Assert.assertTrue(matcher1.group(1).equals("22%"));
        Assert.assertTrue(matcher1.group(3).equals("user"));
        Assert.assertTrue(matcher1.group(5).equals("5.6%"));
        Assert.assertTrue(matcher1.group(7).equals("kernel"));


        matcher2.find();
        Assert.assertTrue(matcher2.group(1).equals("2.5%"));
        Assert.assertTrue(matcher2.group(5).equals("15%"));


        matcher3.find();
        Assert.assertTrue(matcher3.group(1).equals("2.5%"));
        Assert.assertTrue(matcher3.group(5).equals("1.5%"));

        matcher4.find();
        Assert.assertTrue(matcher4.group(1).equals("22.5%"));
        Assert.assertTrue(matcher4.group(5).equals("1.58%"));

        matcher5.find();
        System.out.println(matcher5.group(0));
        System.out.println(matcher5.group(3));

        System.out.println(matcher5.group(1));
        System.out.println(matcher5.group(7));
        System.out.println(matcher5.group(5));



    }


    @Test
    public void test() {
        List<String> activityDetails = new ArrayList<>();
        activityDetails.add("mCurrentFocus=Window{2a936ab u0 com.android.launcher3/com.android.launcher3.Launcher}");
        activityDetails.add("mFocusedApp=AppWindowToken{3a947915 token=Token{2a5eb2cc ActivityRecord{3f6174ff u0 com.android.launcher3/.Launcher t10}}}");
        Optional<String> mCurrentFocus = activityDetails.stream().filter(line -> line.startsWith("mCurrentFocus")).findFirst();
        if(mCurrentFocus.isPresent()) {
            String activity = Arrays.stream(mCurrentFocus.get().split("\\.")).filter(word -> word.endsWith("}")).findFirst().get().replaceAll("}","");
            System.out.println(activity);
        }
    }




    @Test
    public void foo() {
        String check1 = "--say-hello-to-optimus;test-outline;;2";
        String check2 = "--say-hello-to-optimus;test-outline";

        Pattern p = Pattern.compile("((.*?);)(.*)(;;[0-9+])?");
        Matcher matcher1 = p.matcher(check1);
        Matcher matcher2 = p.matcher(check2);

        matcher1.find();


        Assert.assertEquals("--say-hello-to-optimus", matcher1.group(2));
        Assert.assertEquals("test-outline;;2", matcher1.group(3));

        matcher2.find();

        Assert.assertEquals("--say-hello-to-optimus", matcher2.group(2));
        Assert.assertEquals("test-outline", matcher2.group(3));

    }

}
