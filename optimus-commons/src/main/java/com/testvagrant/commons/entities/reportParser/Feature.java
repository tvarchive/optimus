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
 */

package com.testvagrant.commons.entities.reportParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class Feature {

    private JsonObject featureObject;
    private String name;

    public Feature(JsonObject featureObject) {
        this.featureObject = featureObject;
    }

    public boolean hasBackground() {
        JsonArray scenarioArray = featureObject.get("elements").getAsJsonArray();
        return scenarioArray.get(0).getAsJsonObject().get("type").getAsString().equalsIgnoreCase("background");
    }

    public JsonArray getScenarioArray() {
        return featureObject.get("elements").getAsJsonArray();

    }


    public List<JSONObject> even(List<JSONObject> list) {
        if (list.isEmpty()) {
            throw new Error();
        }
        List<JSONObject> toRemove = list.stream().filter(jsonObject -> list.indexOf(jsonObject) % 2 == 1).collect(Collectors.toList());

        list.removeAll(toRemove);
        return list;
    }
}
