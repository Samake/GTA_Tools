package com.gtasa.plain;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GTAPlainIPL {
	
	private String path;
	private String name;
	private String ipl;
	
	public GTAPlainIPL(String path, String content) {
		this.path = path;
		this.ipl = content;
		
		setName();
	}
	
	private void setName() {
		Path filePath = Paths.get(this.path);
		this.name = filePath.getFileName().toString();
	}
	
	public String getPath() {
		return this.path;
	}

	public String getIPL() {
		return this.ipl;
	}

	public String getName() {
		return this.name;
	}
}
