package com.gtasa.gui;

import java.util.List;

import com.gtasa.container.CObject;
import com.gtasa.core.IPLParser;
import com.gtasa.main.Main;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class GUIResultTab {
	
	public static void generatePlainResult() {
		List<CObject> objects = IPLParser.getSearchResults();
		
		String result = "";
		
		if (!objects.isEmpty()) {
			for (CObject object : objects)  {
				result = result + 
						object.getModelID() + ", " +
						object.getName() + ", " +
						object.getInterior() + ", " +
						object.getPosition().getX() + ", " +
						object.getPosition().getY() + ", " +
						object.getPosition().getZ() + ", " +
						object.getRotation().getX() + ", " +
						object.getRotation().getY() + ", " +
						object.getRotation().getZ() + ", " +
						object.getLOD() + "\n";
			}
		}
		
		Main.addTab(buildGUI("Plain result", result));
	}
	
	public static void generateLuaResult() {
		List<CObject> objects = IPLParser.getSearchResults();
		
		String result = "local parsedObjects = {\n";
		
		if (!objects.isEmpty()) {
			for (CObject object : objects)  {
				result = result + "    {" +
						"modelID = " + object.getModelID() + ", " +
						"name = " + object.getName() + ", " +
						"interior = " + object.getInterior() + ", " +
						"posX = " + object.getPosition().getX() + ", " +
						"posY = " + object.getPosition().getY() + ", " +
						"posZ = " + object.getPosition().getZ() + ", " +
						"rotX = " + object.getRotation().getX() + ", " +
						"rotY = " + object.getRotation().getY() + ", " +
						"rotZ = " + object.getRotation().getY() + ", " +
						"lod = " + object.getLOD() + "},\n";
			}
		}
		
		result = result + "}";
		
		result = result.replace("},\n}", "}\n}");
		
		Main.addTab(buildGUI("LUA result", result));
	}
	
	public static void generateIPLResult(String name, String content) {
		Main.addTab(buildGUI(name, content));
	}
	
	private static Tab buildGUI(String title, String result) {
		Tab tab = new Tab();
        tab.setText(title);
        tab.setId(title);
        
        VBox box = new VBox();
        box.setSpacing(4);
        box.setAlignment(Pos.TOP_LEFT);
        
        TextArea resultArea = new TextArea(result);
        resultArea.prefHeightProperty().bind(Main.getSzene().heightProperty());
        resultArea.prefWidthProperty().bind(Main.getSzene().widthProperty());
		
        box.getChildren().addAll(resultArea);
        
        tab.setContent(box);
        
        return tab;
	}
}
