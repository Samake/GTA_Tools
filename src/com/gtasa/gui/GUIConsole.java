package com.gtasa.gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class GUIConsole {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
	private static TextArea consoleArea;
	
	public static Node getContent() {
		VBox pane = new VBox();
		pane.setSpacing(4);
		pane.setAlignment(Pos.TOP_LEFT);
		
		consoleArea = new TextArea();
		consoleArea.setMinSize(620, 200);
		consoleArea.setMaxSize(620, 200);
		
		pane.getChildren().addAll(consoleArea);
		
		return pane;
	}
	
	public static void output(String line) {
		if (consoleArea != null) {
			consoleArea.appendText(dateFormat.format(new Date()) + " >> " + line + "\n");
		}
	}
	
	public static void reset() {
		if (consoleArea != null) {
			consoleArea.clear();
		}
	}
}
