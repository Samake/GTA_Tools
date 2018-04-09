package com.gtasa.core;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.gtasa.binary.GTA3IMG;
import com.gtasa.container.CObject;
import com.gtasa.defines.Common;
import com.gtasa.gui.GUIConsole;
import com.gtasa.math.Quaternion;
import com.gtasa.math.Vector3;
import com.gtasa.plain.GTALibrary;
import com.gtasa.plain.GTAPlainIPL;

public class IPLParser {
	
	private static GTA3IMG imgFile;
	private static GTALibrary library;
	
	private static String[] modelIDs;
	private static List<CObject> parsedObjects = new ArrayList<CObject>();
	
	public static void start() throws Exception {
		GUIConsole.reset();
		parsedObjects.clear();
		
		if (library == null) {
			GUIConsole.output("Loading GTA library...");
			library = new GTALibrary(PropertiesHandler.getGTAPath() + Common.GTA_SA_MAPS);
			
			if (library != null) {
				GUIConsole.output("GTA Library loaded sucessfully!");
			} else {
				GUIConsole.output("Loading GTA Library FAILED!");
			}
		}
		
		if (imgFile == null) {
			GUIConsole.output("Decompiling gta3.img...");
			Path path = Paths.get(PropertiesHandler.getGTAPath() + Common.GTA_SA_GTA_3_IMG);
			
			imgFile = new GTA3IMG(Files.readAllBytes(path));
			
			if (imgFile != null) {
				GUIConsole.output("gta3.img decompiled sucessfully!");
			} else {
				GUIConsole.output("Decompiling gta3.img FAILED!");
			}
		}
		
		modelIDs = PropertiesHandler.getModelIDs().split(",");
		
		GUIConsole.output("Start model search with " + modelIDs.length + " model ID's");
		
		parseIPLFiles();
		
		GUIConsole.output("Search process finished! " + parsedObjects.size() + " objects found finally.");
	}
	
	
	private static void parseIPLFiles() throws Exception {
		
		if (library != null) {
			for (GTAPlainIPL file : library.getIPLs()) {
				int count = 0;
				
				String fileContent = file.getIPL();
				
				int startIndex = fileContent.indexOf("inst") + 4;
				int endIndex = fileContent.indexOf("end");
				
				String iplObjectBlock = fileContent.substring(startIndex, endIndex);
				
				BufferedReader reader = new BufferedReader(new StringReader(iplObjectBlock));
				
				String line;
				
				while ((line = reader.readLine()) != null) {
					if (line.length() > 0) {
						String[] objectArray = line.split(",");
		        	  
						if (objectArray.length == 11) {
							//ID#, 	DFF Name, 		Interior#, 	X-Coord, 	Y-Coord, 	Z-Coord, 	RotationX, 	RotationY, 	RotationZ, 		RotationR, 		LOD
							//709,	sm_vegvbbigbrn, 0, 			1450.21875, -553.28125, 78.9140625, 0, 			0, 			-0.7086663246, 	0.7055437565, 	-1
							String modelID = objectArray[0].trim();
							String modelName = objectArray[1].trim();
							String interior = objectArray[2].trim();
							float posX = Float.parseFloat(objectArray[3].trim());
							float posY = Float.parseFloat(objectArray[4].trim());
							float posZ = Float.parseFloat(objectArray[5].trim());
							float rotX = Float.parseFloat(objectArray[6].trim());
							float rotY = Float.parseFloat(objectArray[7].trim());
							float rotZ = Float.parseFloat(objectArray[8].trim());
							float rotW = Float.parseFloat(objectArray[9].trim());
							String lod = objectArray[10].trim();
							
							for (int k=0; k < modelIDs.length; k++) {
								if (modelIDs[k].trim().equals(modelID)) {
									float[] euler = Quaternion.convertToEulerDegrees(rotX, rotY, rotZ, rotW);
									
									parsedObjects.add(new CObject(modelID, modelName, interior, new Vector3(posX, posY, posZ), new Vector3(euler[0], euler[1], euler[2]), lod));
									count++;
								}
				        	}
						}
					}
				}
				
				if (count > 0) {
					GUIConsole.output(count + " objects were found in " + file.getPath() + " and added to list");
				}
			}
		}
		
		if (imgFile != null) {
			
		}
	}
	
	public static GTA3IMG getIMG() {
		return imgFile;
	}
	
	public static GTALibrary getLibrary() {
		return library;
	}
	
	public static List<CObject> getSearchResults() {
		return parsedObjects;
	}
	
	public static int getSearchResultSize() {
		return parsedObjects.size();
	}
}
