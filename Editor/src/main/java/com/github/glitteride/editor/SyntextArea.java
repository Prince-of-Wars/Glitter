package com.github.glitteride.editor;

import com.github.glitteride.syntext.syntax.SyntaxDefinition;
import com.github.glitteride.syntext.syntax.SyntaxRule;
import com.github.glitteride.syntext.text.SyntaxRange;
import com.github.glitteride.syntext.text.SyntaxRuleRange;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SyntextArea extends CodeArea {
	private SyntaxDefinition syntax;
	private SyntaxRange content;

	public SyntextArea() {
		textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				// TODO: processing the entire document for every edit seems a bit extreme - look for optimizations
				if(getContent().isPresent()) {
					getContent().get().setContent(newText);

					StyleSpansBuilder<Collection<String>> spanBuilder = new StyleSpansBuilder<Collection<String>>();

					int lastMatchEnd = 0;
					List<String> emptyList = Collections.emptyList(); // IntelliJ requires this little workaround in order for types to play nicely; IntelliJ please...
					for(SyntaxRuleRange range : getContent().get().getRuleRanges()) {
						SyntaxRange content = range.getContent();
						SyntaxRule rule = range.getRule();

						spanBuilder.add(emptyList, content.getStart() - lastMatchEnd);
						spanBuilder.add(Collections.singleton(rule.getName()), content.length());

						lastMatchEnd = content.getEnd();
					}

					spanBuilder.add(emptyList, newText.length() - lastMatchEnd);
					setStyleSpans(0, spanBuilder.create());
				}
			}
		});
	}

	public Optional<SyntaxDefinition> getSyntax() {
		return Optional.of(syntax);
	}

	public void setSyntax(SyntaxDefinition syntax) {
		this.syntax = syntax;
		content = new SyntaxRange(getSyntax().get(), getText());
	}

	protected Optional<SyntaxRange> getContent() {
		return Optional.ofNullable(content);
	}
}
