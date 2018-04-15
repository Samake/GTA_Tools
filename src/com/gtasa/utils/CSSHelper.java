package com.gtasa.utils;

import java.io.File;

import javafx.scene.Scene;

public class CSSHelper {
	private static final String USERDIR = System.getProperty("user.dir");
	private static final String STYLEPATHONE = "\\bin\\styles\\style.css";
	private static final String STYLEPATHTWO = "\\styles\\style.css";

	public static void setStyle(Scene scene) {
		File cssPath1 = new File(USERDIR + STYLEPATHONE);
		File cssPath2 = new File(USERDIR + STYLEPATHTWO);

		if (cssPath1.exists()) {
			scene.getStylesheets().add("file:/" + cssPath1.toString().replace("\\", "/"));
		} else if (cssPath2.exists()) {
			scene.getStylesheets().add("file:/" + cssPath2.toString().replace("\\", "/"));
		}
	}
}
