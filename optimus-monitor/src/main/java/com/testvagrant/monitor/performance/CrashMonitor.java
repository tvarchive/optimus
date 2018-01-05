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

import com.mongodb.MongoClient;
import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.performance.Exceptions;
import com.testvagrant.mdb.android.Android;
import com.testvagrant.monitor.services.ScenariosServiceImpl;

import java.util.Optional;

/**
 * Created by abhishek on 12/05/17.
 */
public class CrashMonitor {
    MongoClient mongoClient;

    private SmartBOT smartBOT;

    public CrashMonitor(SmartBOT smartBOT) {
        this.smartBOT = smartBOT;
    }

    public void captureCrashes() {
        Optional<Exceptions> exception = new Android().getException(smartBOT);
        exception.ifPresent(exceptions -> {
            new ScenariosServiceImpl().updateCrashes(smartBOT, exceptions.getStacktrace(), exceptions.getActivityName());
        });

    }

}
