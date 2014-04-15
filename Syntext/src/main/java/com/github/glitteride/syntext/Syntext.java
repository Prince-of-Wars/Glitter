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
package com.github.glitteride.syntext;

import com.github.glitteride.syntext.syntax.SyntaxDefinition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Syntext {
	private static Map<String, SyntaxDefinition> syntaxes = new HashMap<String, SyntaxDefinition>();

	public static SyntaxDefinition create(String name) {
		SyntaxDefinition syntax = new SyntaxDefinition(name);
		syntaxes.put(name, syntax);
		return syntax;
	}

	public static Collection<SyntaxDefinition> getAll() {
		return syntaxes.values();
	}

	public static Optional<SyntaxDefinition> get(String name) {
		return Optional.ofNullable(syntaxes.get(name));
	}

	public static Optional<SyntaxDefinition> delete(String name) {
		return Optional.ofNullable(syntaxes.remove(name));
	}

	public static Collection<SyntaxDefinition> clear() {
		Collection<SyntaxDefinition> syntaxes = Syntext.syntaxes.values();
		Syntext.syntaxes.clear();
		return syntaxes;
	}
}
