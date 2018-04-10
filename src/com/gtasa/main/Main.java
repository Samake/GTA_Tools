package com.gtasa.main;

import com.gtasa.core.PropertiesHandler;
import com.gtasa.gui.GUI;
import com.gtasa.gui.GUIConsole;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class Main extends Application {
	private final String appTitle = "GTA:SA Model Search by Sam@ke";
	private final String appVersion = "v0.2.2b";
	
	private static Scene scene;
	private static TabPane tabPane;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    		
    	PropertiesHandler.init();
    	
        primaryStage.setTitle(appTitle + " | " + appVersion);
        
        Group root = new Group();
        scene = new Scene(root, 640, 420, Color.FLORALWHITE);
        
        tabPane = new TabPane();
        
        BorderPane borderPane = new BorderPane();
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        Tab tab = new Tab();
        tab.setText("Main");
        tab.setId("Main");
        tab.setContent(GUI.getContent(primaryStage));
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
        
        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        
        GUIConsole.output(appTitle + " | " + appVersion + " was started");
    }
    
    public static void addTab(Tab tab) {
    	if (tab != null) {
    		boolean isAdded = false;
    		
    		for (Tab lTab : tabPane.getTabs()) {
    			if (lTab.getId().equals(tab.getId())) {
    				int index = tabPane.getTabs().indexOf(lTab);
    				tabPane.getTabs().set(index, tab);
    				isAdded = true;
    			}
    		}
    		
    		if (isAdded == false) {
    			tabPane.getTabs().add(tab);
    		}
    	}
    }
    
    
    public static Scene getSzene() {
    	return scene;
    }
}