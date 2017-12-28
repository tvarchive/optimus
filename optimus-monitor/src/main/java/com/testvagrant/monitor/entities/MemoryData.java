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

package com.testvagrant.monitor.entities;


public class MemoryData {

    private double total;

    private double actual;

    public double getTotal() {
        return total;
    }

    public MemoryData setTotal(double total) {
        this.total = total;
        return this;
    }

    public double getActual() {
        return actual;
    }

    public MemoryData setActual(double actual) {
        this.actual = actual;
        return this;
    }
}
