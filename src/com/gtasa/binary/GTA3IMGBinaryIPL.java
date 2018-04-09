package com.gtasa.binary;

import java.nio.charset.StandardCharsets;

public class GTA3IMGBinaryIPL {
	
	private byte[] ipl;
	private byte[] identifier = new byte[4];
	
	public GTA3IMGBinaryIPL(int offset, int size, byte[] content) {
		this.ipl = content;
		
		int index = 0;
		System.arraycopy(this.ipl, index, this.identifier, 0, this.identifier.length);
		
		//System.err.println("Offset: " + offset + ", Size: " + size + " | " + getIdentifier());
	}

	public byte[] getIPL() {
		return this.ipl;
	}
	
	public String getIdentifier() {
		return new String(this.identifier, StandardCharsets.ISO_8859_1);
	}
	
	public int getLength() {
		return this.ipl.length;
	}
}
