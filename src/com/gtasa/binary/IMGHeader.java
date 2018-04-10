package com.gtasa.binary;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.gtasa.core.FileSystem;
import com.sun.jna.platform.win32.WinDef.DWORD;

public class IMGHeader {
	
	private byte[] header = new byte[8];
	private byte[] version = new byte[4];
	private DWORD entries = new DWORD();
	
	public IMGHeader(byte[] content) {
		int index = 0;
		
		System.arraycopy(content, index, this.header, 0, this.header.length);
		System.arraycopy(this.header, index, this.version, 0, this.version.length);
		index = index + this.version.length;
		
		this.entries.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.header, index, index + DWORD.SIZE)));
	}
	
	public byte[] getHeader() {
		return this.header;
	}
	
	public String getVersion() {
		return new String(this.version, StandardCharsets.ISO_8859_1);
	}
	
	public int getEntries() {
		return this.entries.intValue();
	}
	
	public int getLength() {
		return this.header.length;
	}
}
