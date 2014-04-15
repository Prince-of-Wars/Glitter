/*
 * Copyright 2014 Team Glitter <https://github.com/GlitterIDE/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.glitteride.editor.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.Set;

public class JsonObject {
	private JSONObject object;

	public JsonObject(String json) {
		this((JSONObject)JSONValue.parse(json));
	}

	public JsonObject() {
		this(new JSONObject());
	}

	public JsonObject(JSONObject object) {
		this.object = object;
	}

	protected JSONObject getObject() {
		return object;
	}

	public Set<Object> keySet() {
		return getObject().keySet();
	}

	public boolean containsKey(Object key) {
		return getObject().containsKey(key);
	}

	public Object get(Object key) {
		return getObject().get(key);
	}

	public JsonObject getNestedObject(Object key) {
		return new JsonObject((JSONObject)get(key));
	}

	public JSONArray getNestedArray(Object key) {
		return (JSONArray)get(key);
	}

	public <T> T getOrDefault(Object key, T value) {
		return getObject().containsKey(key) ? (T)getObject().get(key) : value;
	}

	public boolean getBoolean(Object key) {
		if(getObject().containsKey(key)) {
			if(get(key) instanceof Boolean) {
				return (Boolean)get(key);
			} else {
				return getLong(key) > 0;
			}
		} else {
			return false;
		}
	}

	public String getString(Object key) {
		if(getObject().containsKey(key)) {
			return (String)get(key);
		} else {
			return null;
		}
	}

	public long getLong(Object key) {
		if(getObject().containsKey(key)) {
			return (Long)get(key);
		} else {
			return -1;
		}
	}

	public int getInt(Object key) {
		return (int)getLong(key);
	}

	public double getDouble(Object key) {
		if(getObject().containsKey(key)) {
			return (Double)get(key);
		} else {
			return -1;
		}
	}

	public void put(Object key, Object value) {
		object.put(key, value);
	}

	@Override
	public String toString() {
		return getObject().toJSONString();
	}
}
