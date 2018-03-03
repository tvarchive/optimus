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

package com.testvagrant.optimus.parser;

import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.monitor.services.IntellisenseServiceImpl;
import com.testvagrant.optimus.recommender.ExceptionCollator;
import org.apache.commons.io.FilenameUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class ReportParserTest {

    @Test
    public void foo() throws IOException {
        List<ExecutedScenario> executedScenarios = new ReportParser(new File("src/test/resources/reports/cucumber"), latestBuildId).parse();
        for (ExecutedScenario executedScenario : executedScenarios) {
            Assert.assertNotNull(executedScenario);
        }
    }

    @Test
    public void foo1() throws IOException {
        List<ExecutedScenario> executedScenarios = new ReportParser(new File("src/test/resources/reports/cucumber"), latestBuildId).parse();
        for (ExecutedScenario executedScenario : executedScenarios) {
            Assert.assertNotNull(executedScenario);
        }
    }

    @Test
    public void test() throws IOException {
        List<ExecutedScenario> executedScenarios = new ReportParser(new File("src/test/resources/jsonreports"), latestBuildId).parse();
        ExceptionCollator exceptionCollator = new ExceptionCollator(executedScenarios);
        Map<String, List<ExecutedScenario>> collate = exceptionCollator.collate();
        new IntellisenseServiceImpl().writeFailedScenariosByException(collate);


    }

    @Test
    public void testUriExtraction(){
        String uri = "/Users/sukeshkumar/Development/projects/makemytrip-2.0/src/test/resources/features/MMT.feature";
        String x = Stream.of(uri.split("/")).reduce((first, last) -> last).get();
        Assert.assertEquals("MMT.feature", x);
        Assert.assertEquals("MMT", FilenameUtils.getBaseName(x));

    }
}
