package com.gtasa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesHandler {

	private static Properties mainProperties;
	private static String gtaPath;
	private static String modelIDS;

	public static void init() throws Exception, IOException {
		mainProperties = new Properties();
		mainProperties.load(new FileInputStream(new File(System.getProperty("basePath"), "settings/settings.properties")));
		
		gtaPath = mainProperties.getProperty("path");
		modelIDS = mainProperties.getProperty("models");
	}

	public static String getGTAPath() {
		return gtaPath;
	}

	public static void setGTAPath(String path) {
		gtaPath = path;
		mainProperties.setProperty("path", gtaPath);
	}
	
	
	public static String getModelIDs() {
		return modelIDS;
	}

	public static void setModelIDs(String ids) {
		modelIDS = ids;
		mainProperties.setProperty("models", modelIDS);
	}

	public static void save() throws Exception {
		File file = new File(System.getProperty("basePath"), "settings/settings.properties");
        OutputStream out = new FileOutputStream(file);

        mainProperties.store(out, "Saved manually!");
	}
}
