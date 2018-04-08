package com.gtasa.binary;


public class GTA3IMGBinaryIPL {
	
	private byte[] ipl;
	private byte[] identifier = new byte[4];
	
	public GTA3IMGBinaryIPL(byte[] content) {
		this.ipl = content;
		
		int index = 0;
		System.arraycopy(this.ipl, index, this.identifier, 0, this.identifier.length);
		
		System.err.println(getIdentifier());
	}

	public byte[] getIPL() {
		return this.ipl;
	}
	
	public String getIdentifier() {
		return new String(this.identifier);
	}
	
	public int getLength() {
		return this.ipl.length;
	}
}
