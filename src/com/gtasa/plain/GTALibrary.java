package com.gtasa.plain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.gtasa.core.FileSystem;
import com.gtasa.gui.GUIConsole;
import com.gtasa.main.Main;
import com.gtasa.main.PreLoad;

public class GTALibrary {
	
	private String path;
	
	private List<GTAPlainIPL> iplFiles = new ArrayList<GTAPlainIPL>();
	
	public GTALibrary(String path) throws Exception {
		this.path = path;
		
		GUIConsole.output("Buffering plain IPL files from library");
		loadIPLs();
		GUIConsole.output(this.iplFiles.size() + " IPL files were found");
	}
	
	private void loadIPLs() throws Exception {
		File[] directoryList = FileSystem.readLibray(path);
    	
		int count = 0;
		
    	for (int i=0; i < directoryList.length; i++) {
    		if (directoryList[i] != null) {
    			if (directoryList[i].isDirectory() && directoryList[i].isAbsolute()) {
    				String subPath = path + "\\" + directoryList[i].getName();
    				
    				File[] fileList = FileSystem.readLibray(subPath);
    				
    				for (int j=0; j < fileList.length; j++) {
                		if (fileList[j] != null) {
                			if (fileList[j].toString().contains(".ipl")) {
                				GTAPlainIPL ipl = new GTAPlainIPL(fileList[j].toString(), FileSystem.openIPL(fileList[j].toString()));
                				
                				count++;
                				double progress = (49 * count) / 40;
                				Main.setProgress((double) 1 + progress);
                				PreLoad.setStatus("Caching plain ipl " + ipl.getName() + "...");
                				
                				this.iplFiles.add(ipl);
                			}
                		}
    				}
    			}
    		}
        }
	}
	
	public List<GTAPlainIPL> getIPLs() {
		return this.iplFiles;
	}

	public String getPath() {
		return this.path;
	}
}
