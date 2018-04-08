package com.gtasa.plain;

public class GTAPlainIPL {
	
	private String path;
	private String ipl;
	
	public GTAPlainIPL(String path, String content) {
		this.path = path;
		this.ipl = content;
	}
	
	public String getPath() {
		return this.path;
	}

	public String getIPL() {
		return this.ipl;
	}
}
