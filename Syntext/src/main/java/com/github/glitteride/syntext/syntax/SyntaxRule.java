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
