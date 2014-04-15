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

import com.github.glitteride.syntext.text.SyntaxRuleRange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SyntaxDefinition {
	private String name;
	private List<SyntaxRule> rules;

	public SyntaxDefinition(String name) {
		rules = new ArrayList<SyntaxRule>();
		setName(name);
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public Collection<SyntaxRule> getRules() {
		return Collections.unmodifiableList(rules);
	}

	public void createRule(String name, String regex) {
		rules.add(new SyntaxRule(this, name, regex));
	}

	public List<SyntaxRuleRange> match(CharSequence content) {
		List<SyntaxRuleRange> ranges = new ArrayList<SyntaxRuleRange>();

		for(SyntaxRule rule : rules) {
			ranges.addAll(rule.match(content));
		}

		Collections.sort(ranges);
		return ranges;
	}
}
