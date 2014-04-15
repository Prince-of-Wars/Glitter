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
package com.github.glitteride.syntext.syntax;

import com.github.glitteride.syntext.text.SyntaxRange;
import com.github.glitteride.syntext.text.SyntaxRuleRange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxRule {
	private SyntaxDefinition syntax;
	private String name;
	private Pattern pattern;

	public SyntaxRule(SyntaxDefinition syntax, String name, Pattern pattern) {
		setSyntax(syntax);
		setName(name);
		setPattern(pattern);
	}

	public SyntaxRule(SyntaxDefinition syntax, String name, String regex) {
		this(syntax, name, Pattern.compile(regex));
	}

	public SyntaxDefinition getSyntax() {
		return syntax;
	}

	protected void setSyntax(SyntaxDefinition syntax) {
		this.syntax = syntax;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public Pattern getPattern() {
		return pattern;
	}

	protected void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public List<SyntaxRuleRange> match(int prefixCount, CharSequence content) {
		List<SyntaxRuleRange> ranges = new ArrayList<SyntaxRuleRange>();
		Matcher matcher = getPattern().matcher(content);

		while(matcher.find()) {
			int start = matcher.start();
			ranges.add(new SyntaxRuleRange(this, new SyntaxRange(getSyntax(), prefixCount + start, content.subSequence(start, matcher.end()))));
		}

		return ranges;
	}

	public Collection<SyntaxRuleRange> match(CharSequence content) {
		return match(0, content);
	}
}
