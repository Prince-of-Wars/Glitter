package com.github.glitteride.editor;

import com.github.glitteride.syntext.Syntext;
import com.github.glitteride.syntext.syntax.SyntaxDefinition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launcher extends Application {
	public static void launch(String... args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Glitter");
		SyntextArea codeArea = new SyntextArea();

		SyntaxDefinition syntax = Syntext.create("Test");
		syntax.createRule("keyword", "\\bthe\\b");
		syntax.createRule("test", "\\bbrown\\b");
		syntax.createRule("italic", "\\bquick\\b");
		codeArea.setSyntax(syntax);
		codeArea.replaceText(0, 0, "the quick brown fox jumps over the lazy dog");

		StackPane root = new StackPane();
		root.getChildren().add(codeArea);
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		primaryStage.setScene(scene);
		codeArea.requestFocus();
		primaryStage.show();
	}
}
