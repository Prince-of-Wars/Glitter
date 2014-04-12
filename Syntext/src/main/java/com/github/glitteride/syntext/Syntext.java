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
