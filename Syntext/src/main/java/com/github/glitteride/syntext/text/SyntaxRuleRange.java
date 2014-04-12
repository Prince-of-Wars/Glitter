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
