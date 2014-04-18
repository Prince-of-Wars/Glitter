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
		syntax.createRule("string", "\"(?:\\.|(\\\\\\\")|[^\\\"\"\\n])*\"");
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
