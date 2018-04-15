package com.gtasa.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gtasa.binary.IMG;
import com.gtasa.defines.Common;
import com.gtasa.plain.GTALibrary;
import com.gtasa.utils.PropertiesHandler;

public class FileCache {
	
	private static IMG imgFile;
	private static GTALibrary library;

	public static void init() throws Exception {
		System.out.println("Loading GTA library...");
		
		library = new GTALibrary(PropertiesHandler.getGTAPath() + Common.GTA_SA_MAPS);
		
		if (library != null) {
			System.out.println("GTA Library were loaded sucessfully!");
		} else {
			System.out.println("Loading GTA Library FAILED!");
		}
		
		System.out.println("Loading gta3.img...");
		Path path = Paths.get(PropertiesHandler.getGTAPath() + Common.GTA_SA_GTA_3_IMG);
		
		imgFile = new IMG(Files.readAllBytes(path));
		
		if (imgFile != null) {
			System.out.println("gta3.img were decompiled sucessfully!");
		} else {
			System.out.println("Decompiling gta3.img FAILED!");
		}
	}
	
	public static IMG getIMG() {
		return imgFile;
	}
	
	public static GTALibrary getLibrary() {
		return library;
	}
}
