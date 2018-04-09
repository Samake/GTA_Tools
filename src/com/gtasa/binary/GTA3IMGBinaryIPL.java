package com.gtasa.binary;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.gtasa.core.FileSystem;

public class GTA3IMGBinaryIPL {
	
	private GTA3IMGDirectory directory;
	private byte[] bytes;
	private byte[] identifier = new byte[4];
	private String ipl;
	
	public GTA3IMGBinaryIPL(GTA3IMGDirectory directory, byte[] content) {
		this.directory = directory;
		this.bytes = content;
		
		int index = 0;
		System.arraycopy(this.bytes, index, this.identifier, 0, this.identifier.length);
		
		if (getIdentifier().equals("bnry")) {
			int sectorSize = this.bytes.length / 16; // for test only
			this.ipl = String.valueOf(FileSystem.bytesToChars(Arrays.copyOfRange(this.bytes, index, sectorSize)));
		}
	}

	public GTA3IMGDirectory getDirectory() {
		return this.directory;
	}
	
	public byte[] getBytes() {
		return this.bytes;
	}
	
	public String getIPL() {
		return this.ipl;
	}
	
	public String getIdentifier() {
		return new String(this.identifier, StandardCharsets.ISO_8859_1);
	}
	
	public int getLength() {
		return this.bytes.length;
	}
}
