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

package com.testvagrant.optimus.builder;

import com.testvagrant.commons.entities.reportParser.Step;

public class StepBuilder {

    private Step step = new Step();

    public Step build() {
        return step;
    }

    public StepBuilder withStatus(String status) {
        step.setStatus(status);
        return this;
    }

    public StepBuilder withErrorMessage(String errorMessage) {
        step.setError_message(errorMessage);
        return this;
    }

    public StepBuilder withName(String name) {
        step.setName(name);
        return this;
    }

    public StepBuilder withKeyword(String keyword) {
        step.setKeyword(keyword);
        return this;
    }

    public StepBuilder withDuration(Long duration) {
        step.setDuration(duration);
        return this;
    }
}
