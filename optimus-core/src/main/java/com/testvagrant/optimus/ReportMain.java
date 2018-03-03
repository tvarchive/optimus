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

import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.commons.utils.JsonUtil;
import com.testvagrant.monitor.clients.BuildsClient;
import com.testvagrant.monitor.services.BuildsServiceImpl;
import com.testvagrant.monitor.services.IntellisenseServiceImpl;
import com.testvagrant.monitor.services.ScenariosServiceImpl;
import com.testvagrant.optimus.parser.OptimusConfigParser;
import com.testvagrant.optimus.parser.ReportParser;
import com.testvagrant.optimus.recommender.ExceptionCollator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReportMain {

    public static void main(String[] args) throws IOException {
        System.out.println("Parsing report");
        String latestBuildId = new BuildsClient().getLatestBuildId();
        List<ExecutedScenario> scenarios = new ReportParser(new File("build/reports/cucumber"), latestBuildId).parse();
        if(monitoringIsOn()) {
            new ScenariosServiceImpl().updateExecutionDetailsFor(scenarios);
            ExceptionCollator collator = new ExceptionCollator(scenarios);
            Map<String, List<ExecutedScenario>> collate = collator.collate();
            new IntellisenseServiceImpl().writeFailedScenariosByException(collate);
        }
    }


    private static boolean monitoringIsOn() {
        String testFeed = System.getProperty("testFeed") + ".json";
        String appJson = new JsonUtil().getAppJson(testFeed);
        OptimusConfigParser optimusConfigParser = new OptimusConfigParser(appJson);
        return optimusConfigParser.isMonitoring();
    }

}
