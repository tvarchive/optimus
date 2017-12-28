package com.testvagrant.mdb.android;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.performance.Activity;
import com.testvagrant.commons.entities.performance.CpuStatistics;
import com.testvagrant.commons.entities.performance.MemoryStatistics;
import com.testvagrant.mdb.core.MDB;

public interface ADB extends MDB {

    /**
     * Captures the Memory at a particular interval. Usage is captured in Total and Actual MegaBytes
     * @param smartBOT
     * @return
     */
    MemoryStatistics getMemoryInfo(SmartBOT smartBOT);

    /**
     * Captures the CPU at a particular interval. Usage is captured in User and Kernel percentage
     * @param smartBOT
     * @return
     */
    CpuStatistics getCpuInfo(SmartBOT smartBOT);

    /**
     * Captures the current focused activity.
     * @param smartBOT
     * @return
     */
    Activity getActivity(SmartBOT smartBOT);

}
