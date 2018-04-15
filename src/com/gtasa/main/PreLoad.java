package com.gtasa.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.gtasa.utils.CSSHelper;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreLoad extends Preloader {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 300;

    private Stage preloaderStage;
    private Scene scene;

    private static Text status;
    private Text progress;

    public PreLoad() {

    }

    @Override
    public void init() throws Exception {
        Platform.runLater(() -> {
        	FileInputStream inputstream;
        	Image image = null;
        	BackgroundImage bgImg = null;
        	
			try {
				inputstream = new FileInputStream("res\\logo.png");
				image = new Image(inputstream);
				
				bgImg = new BackgroundImage(image, 
					    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					    BackgroundPosition.DEFAULT, 
					    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	
			status = new Text("Loading, please wait...");
			status.setTextAlignment(TextAlignment.CENTER);
			status.setId("TEXTSMALL");
            
            progress = new Text("0 %");
            progress.setId("BOLDTEXT");

            VBox root = new VBox(status, progress);
            root.setAlignment(Pos.BOTTOM_CENTER);
            root.setBackground(new Background(bgImg));

            scene = new Scene(root, WIDTH, HEIGHT);
            
            CSSHelper.setStyle(scene);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        this.preloaderStage.initStyle(StageStyle.TRANSPARENT);

        preloaderStage.setScene(scene);
        preloaderStage.setResizable(false);
        preloaderStage.centerOnScreen();
        preloaderStage.setAlwaysOnTop(true);
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            progress.setText(((ProgressNotification) info).getProgress() + " %");
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                preloaderStage.hide();
                break;
        }
    }
    
    public static void setStatus(String text) {
    	status.setText(text);
    }
}
