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

import com.github.glitteride.syntext.syntax.SyntaxDefinition;

import java.util.*;

public class SyntaxRange implements CharSequence {
	private SyntaxDefinition syntax;
	private CharSequence content;
	private int start;
	private List<SyntaxRuleRange> ruleRanges;

	public SyntaxRange(SyntaxDefinition syntax, int start, CharSequence content) {
		setSyntax(syntax);
		setStart(start);
		_setContent(content);
		ruleRanges = new ArrayList<SyntaxRuleRange>();
	}

	public SyntaxRange(SyntaxDefinition syntax, CharSequence content) {
		this(syntax, 0, content);
	}

	public SyntaxDefinition getSyntax() {
		return syntax;
	}

	public void setSyntax(SyntaxDefinition syntax) {
		this.syntax = syntax;
	}

	public int getStart() {
		return start;
	}

	protected void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return start + length();
	}

	protected CharSequence getContent() {
		return content;
	}

	protected void _setContent(CharSequence content) {
		this.content = content;
	}

	public List<SyntaxRuleRange> getRuleRanges() {
		return ruleRanges;
	}

	public Optional<SyntaxRuleRange> getLastRange() {
		if(ruleRanges.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(ruleRanges.get(ruleRanges.size() - 1));
	}

	protected void setRuleRanges(Collection<SyntaxRuleRange> ranges) {
		ruleRanges.clear();
		ruleRanges.addAll(ranges);
		Collections.sort(ruleRanges);
	}

	public void setContent(CharSequence content) {
		if(!content.equals(getContent())) {
			_setContent(content);
			match();
		}
	}

	public Collection<SyntaxRuleRange> match() {
		setRuleRanges(getSyntax().match(this));
		return getRuleRanges();
	}

	public Collection<SyntaxRuleRange> clean() {
		List<SyntaxRuleRange> ranges = new ArrayList<SyntaxRuleRange>();
		Iterator<SyntaxRuleRange> iterator = getRuleRanges().iterator();

		while(iterator.hasNext()) {
			SyntaxRuleRange range = iterator.next();
			if(range.getContent().getStart() < length() || range.getContent().getEnd() < length()) {
				iterator.remove();
				ranges.add(range);
			}
		}

		return Collections.unmodifiableCollection(ranges);
	}

	@Override
	public int length() {
		return getContent().length();
	}

	@Override
	public char charAt(int index) {
		return getContent().charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return getContent().subSequence(start, end);
	}

	@Override
	public String toString() {
		return content.toString();
	}
}
