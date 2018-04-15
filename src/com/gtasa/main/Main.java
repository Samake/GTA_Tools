package com.gtasa.main;

import java.io.File;

import com.gtasa.core.FileCache;
import com.gtasa.gui.GUI;
import com.gtasa.gui.GUIConsole;
import com.gtasa.utils.CSSHelper;
import com.gtasa.utils.PropertiesHandler;
import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application {
	public static Main application;
	
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    
	private final String appTitle = "GTA:SA Model Search by Sam@ke";
	private final String appVersion = "v0.3.2b";
	
	private static Scene scene;
	private static TabPane tabPane;

    private static final int COUNT_LIMIT = 200000;

    private Stage applicationStage;

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, PreLoad.class, args);
    }
    
    public Main() {
    	application = this;
    }

    @Override
    public void init() throws Exception {

        setProgress(0.0);
        PropertiesHandler.init();
        setProgress(1.0);
        
        if (!PropertiesHandler.getGTAPath().isEmpty()) {
        	FileCache.init();
        } else {
        	//show path window
        }
        
        setProgress(100.0);
    }
    
    public static void setProgress(double progress) {
    	LauncherImpl.notifyPreloader(application, new PreLoad.ProgressNotification(progress));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        applicationStage = primaryStage;

        applicationStage.setTitle(appTitle + " | " + appVersion);
        
		Pane root = new Pane();

		tabPane = new TabPane();
		tabPane.setPrefWidth(WIDTH);
		tabPane.setPrefHeight(HEIGHT);
		tabPane.setMinSize(WIDTH, HEIGHT);

		root.getChildren().add(tabPane);

		Scale scale = new Scale(1, 1, 0, 0);
		scale.xProperty().bind(root.widthProperty().divide(WIDTH));
		scale.yProperty().bind(root.heightProperty().divide(HEIGHT));
		root.getTransforms().add(scale);

		scene = new Scene(root, WIDTH, HEIGHT);
        
		CSSHelper.setStyle(scene);
        
        BorderPane borderPane = new BorderPane();
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        Tab tab = new Tab();
        tab.setText("Main");
        tab.setId("Main");
        tab.setContent(GUI.getContent(applicationStage));
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
        
        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        
        applicationStage.setScene(scene);
        applicationStage.show();
        applicationStage.setResizable(true);
        
        setLogo(applicationStage);

		scene.rootProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> observable, Parent oldValue, Parent newValue) {
				scene.rootProperty().removeListener(this);
				scene.setRoot(root);
				((Region) newValue).setPrefWidth(WIDTH);
				((Region) newValue).setPrefHeight(HEIGHT);
				root.getChildren().clear();
				root.getChildren().add(newValue);
				scene.rootProperty().addListener(this);
			}
		});
        
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
    
    
    private static void setLogo(Stage primaryStage) {
		File logoPath1 = new File(System.getProperty("user.dir") + "\\bin\\res\\icon.png");
		File logoPath2 = new File(System.getProperty("user.dir") + "\\res\\icon.png");
		File logoPath3 = new File("\\res\\icon.png");

		if (logoPath1.exists()) {
			primaryStage.getIcons().add(new Image("file:/" + logoPath1.toString().replace("\\", "/")));
		} else if (logoPath2.exists()) {
			primaryStage.getIcons().add(new Image("file:/" + logoPath2.toString().replace("\\", "/")));
		} else {
			primaryStage.getIcons().add(new Image("file:/" + logoPath3.toString().replace("\\", "/")));
		}
	}
    
    
    public static Scene getSzene() {
    	return scene;
    }
}
