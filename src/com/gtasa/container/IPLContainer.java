package com.gtasa.container;

public class IPLContainer {
	
	private String type;
	private String name;
	private String path;
	private int offset;
	private int size;
	private String plainIPL;
	private String binaryIPL;
	
	public IPLContainer() {
		
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getOffset() {
		return this.offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getPlainIPL() {
		return this.plainIPL;
	}

	public void setPlainIPL(String plainIPL) {
		this.plainIPL = plainIPL;
	}

	public String getBinaryIPL() {
		return this.binaryIPL;
	}

	public void setBinaryIPL(String binaryIPL) {
		this.binaryIPL = binaryIPL;
	}
}
