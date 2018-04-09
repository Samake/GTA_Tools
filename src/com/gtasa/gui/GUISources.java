package com.gtasa.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gtasa.core.FileSystem;
import com.gtasa.core.IPLParser;
import com.gtasa.main.Main;
import com.gtasa.plain.GTALibrary;
import com.gtasa.plain.GTAPlainIPL;
import com.gtasa.container.IPLContainer;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GUISources {

	private static Map<String, IPLContainer> iplContainers = new HashMap<String, IPLContainer>();
	private static TextField offsetField;
	private static TextField lengthField;
	private static Button iplButton;
	private static IPLContainer selectedIPL = null;
	
	public GUISources() {
		VBox pane = new VBox();
		pane.setPadding(new Insets(5, 10, 10, 10));
		pane.setSpacing(5);
		pane.setAlignment(Pos.TOP_LEFT);
		
		ScrollPane topBox = new ScrollPane();
		topBox.setMinSize(640, 330);
		topBox.setMaxSize(640, 330);
		
		Accordion accordion = new Accordion();  
		
		accordion.expandedPaneProperty().addListener((ObservableValue<? extends TitledPane> ov, TitledPane old_val, 
			TitledPane new_val) -> {
	            if (new_val != null) {
	                System.out.println(accordion.getExpandedPane().getText());
	                selectedIPL = iplContainers.get(accordion.getExpandedPane().getText());
	                
					if (selectedIPL != null) {
						iplButton.setDisable(false);
						
	                	if (selectedIPL.getType().equals("Plain-Text")) {
	                		offsetField.setDisable(true);
	                		lengthField.setDisable(true);
	                	} else {
	                		offsetField.setDisable(false);
	                		lengthField.setDisable(false);
	                	}
	                } else {
	                	iplButton.setDisable(true);
	                }
	            }
		});

		GTALibrary library = IPLParser.getLibrary();
		
		if (library != null) {
			for (GTAPlainIPL ipl : library.getIPLs()) {
				VBox resultBox = new VBox();
				resultBox.setSpacing(4);
				resultBox.setAlignment(Pos.TOP_LEFT);
				resultBox.setMinWidth(640);
				
				HBox firstRow = new HBox();
				firstRow.setSpacing(4);
				firstRow.setAlignment(Pos.TOP_LEFT);
				
				Text typeLabel = new Text("Type: ");
				Text typeValue = new Text("Plain-Text");
				
				firstRow.getChildren().addAll(typeLabel, typeValue);
				
				HBox secondRow = new HBox();
				secondRow.setSpacing(4);
				secondRow.setAlignment(Pos.TOP_LEFT);
				
				Text pathLabel = new Text("Path: ");
				Text pathValue = new Text(ipl.getPath());
				
				secondRow.getChildren().addAll(pathLabel, pathValue);
				
				resultBox.getChildren().addAll(firstRow, secondRow);

				TitledPane resultPane = new TitledPane(ipl.getName(), resultBox);
				accordion.getPanes().add(resultPane);
				
				IPLContainer container = new IPLContainer();
				container.setType("Plain-Text");
				container.setName(ipl.getName());
				container.setPath(ipl.getPath());
				
				iplContainers.put(ipl.getName(), container);
			}
		}
		
		topBox.setContent(accordion);
		
		HBox bottomBox = new HBox();
		bottomBox.setSpacing(8);
		bottomBox.setAlignment(Pos.TOP_LEFT);
		
		VBox offsetBox = new VBox();
		offsetBox.setSpacing(4);
		offsetBox.setAlignment(Pos.TOP_LEFT);
		
		Text offsetLabel = new Text("Offset");
		offsetField = new TextField();
		offsetField.setMinSize(75, 25);
		
		offsetBox.getChildren().addAll(offsetLabel, offsetField);
		
		VBox lengthBox = new VBox();
		lengthBox.setSpacing(4);
		lengthBox.setAlignment(Pos.TOP_LEFT);
		
		Text lengthLabel = new Text("Size");
		lengthField = new TextField();
		lengthField.setMinSize(75, 25);
		
		lengthBox.getChildren().addAll(lengthLabel, lengthField);
		
		iplButton = new Button("Show IPL");
		iplButton.setDisable(true);
		iplButton.setMinSize(100, 45);
		iplButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	if (selectedIPL != null) {
            		if (selectedIPL.getType().equals("Plain-Text")) {
            			try {
							GUIResultTab.generateIPLResult(selectedIPL.getName(), FileSystem.openIPL(selectedIPL.getPath()));
						} catch (IOException e) {
							e.printStackTrace();
						}
            		} else {
            			
            		}
            	}
            }
        });
		
		bottomBox.getChildren().addAll(offsetBox, lengthBox, iplButton);
		
		pane.getChildren().addAll(topBox, bottomBox);
		
		Tab tab = new Tab();
        tab.setText("Sources");
        tab.setId("Sources");
        tab.setClosable(false);
        tab.setContent(pane);
        
        Main.addTab(tab);
	}
}
