package com.gtasa.gui;

import java.io.File;
import com.gtasa.core.IPLParser;
import com.gtasa.core.PropertiesHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GUI {
	
	private static Button luaButton = new Button();
	private static Button plainButton = new Button();

	public static Node getContent(Stage stage) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Open Folder");
		
		VBox pane = new VBox();
		pane.setPadding(new Insets(5, 10, 10, 10));
		pane.setSpacing(5);
		pane.setAlignment(Pos.TOP_LEFT);
		
		HBox firstRow = new HBox();
		firstRow.setSpacing(4);
		firstRow.setAlignment(Pos.TOP_LEFT);
		
		Label pathLabel = new Label("GTA Path: ");
		pathLabel.setMinSize(65, 25);
		
		TextField pathField = new TextField(PropertiesHandler.getGTAPath());
		pathField.setMinSize(500, 25);
		
		Button pathButton = new Button();
		pathButton.setText("Select");
		pathButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	File selectedDirectory = chooser.showDialog(stage);
            	
            	if (selectedDirectory != null) {
            		pathField.setText(selectedDirectory.getAbsolutePath().trim());
            		PropertiesHandler.setGTAPath(selectedDirectory.getAbsolutePath().trim());
            		
            		try {
						PropertiesHandler.save();
					} catch (Exception e) {
						e.printStackTrace();
					}
            	}
            	
            }
        });
		
		firstRow.getChildren().addAll(pathLabel, pathField, pathButton);
		
		Separator separator = new Separator();
		separator.setOrientation(Orientation.HORIZONTAL);
		
		VBox secondRow = new VBox();
		secondRow.setSpacing(4);
		secondRow.setAlignment(Pos.TOP_LEFT);
		
		Label idLabel = new Label("Model ID's: (eg.: 123, 124...)");
		idLabel.setMinSize(65, 25);
		
		TextArea modelIDArea = new TextArea(PropertiesHandler.getModelIDs());
		modelIDArea.setMinSize(620, 75);
		modelIDArea.setMaxSize(620, 75);
		
		secondRow.getChildren().addAll(idLabel, modelIDArea);
		
		HBox thirdRow = new HBox();
		thirdRow.setSpacing(4);
		thirdRow.setAlignment(Pos.TOP_CENTER);
		
		Button parseButton = new Button();
		parseButton.setText("Start search");
		parseButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	try {
            		PropertiesHandler.setModelIDs(modelIDArea.getText());
            		
            		try {
						PropertiesHandler.save();
					} catch (Exception e) {
						e.printStackTrace();
					}
            		
            		IPLParser.start();
            		setButtons();
            		
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
		
		plainButton = new Button();
		plainButton.setText("Show plain results");
		plainButton.setDisable(true);
		plainButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	GUIresult.generatePlainResult();
            }
        });
		
		luaButton = new Button();
		luaButton.setText("Show results as lua table");
		luaButton.setDisable(true);
		luaButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	GUIresult.generateLuaResult();
            }
        });
		
		thirdRow.getChildren().addAll(parseButton, plainButton, luaButton);
		
		Separator separator2 = new Separator();
		separator2.setOrientation(Orientation.HORIZONTAL);
		
		HBox fourthRow = new HBox();
		fourthRow.setSpacing(4);
		fourthRow.setAlignment(Pos.TOP_LEFT);
		
		fourthRow.getChildren().addAll(GUIConsole.getContent());
		
        pane.getChildren().addAll(firstRow, separator, secondRow, thirdRow, separator2, fourthRow);
				
		return pane;
	}
	
	private static void setButtons() {
		if (IPLParser.getSearchResultSize() > 0) {
			plainButton.setDisable(false);
			luaButton.setDisable(false);	
		} else {
			plainButton.setDisable(true);
			luaButton.setDisable(true);	
		}
	}
}
