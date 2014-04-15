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
package com.github.glitteride.syntext.text;

import com.github.glitteride.syntext.syntax.SyntaxRule;

public class SyntaxRuleRange implements Comparable<SyntaxRuleRange> {
	private SyntaxRule rule;
	private SyntaxRange content;

	public SyntaxRuleRange(SyntaxRule rule, SyntaxRange content) {
		setRule(rule);
		setContent(content);
	}

	public SyntaxRule getRule() {
		return rule;
	}

	protected void setRule(SyntaxRule rule) {
		this.rule = rule;
	}

	public SyntaxRange getContent() {
		return content;
	}

	protected void setContent(SyntaxRange content) {
		this.content = content;
	}

	@Override
	public int compareTo(SyntaxRuleRange range) {
		return getContent().getEnd() - range.getContent().getEnd();
	}
}
