package com.gtasa.binary;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.gtasa.core.FileSystem;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;

public class IMGDirectory {
	private final int SECTORSIZE = 2048;
	
	private byte[] directory = new byte[32];
	private DWORD offset = new DWORD();
	private WORD streamingSize = new WORD();
	private WORD sizeInArchive = new WORD();
	private byte[] name = new byte[24];
	
	public IMGDirectory(byte[] content) {
		int index = 0;
		System.arraycopy(content, index, this.directory, 0, this.directory.length);
		
		this.offset.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.directory, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		this.streamingSize.setValue(FileSystem.readShort(Arrays.copyOfRange(this.directory, index, index + WORD.SIZE)));
		index = index + WORD.SIZE;
		
		this.sizeInArchive.setValue(FileSystem.readShort(Arrays.copyOfRange(this.directory, index, index + WORD.SIZE)));
		index = index + WORD.SIZE;
		
		System.arraycopy(content, index, this.name, 0, this.name.length);
	}

	public byte[] getDirectory() {
		return this.directory;
	}
	
	public int getOffset() {
		return this.offset.intValue() * SECTORSIZE;
	}
	
	public int getStreamingSize() {
		return this.streamingSize.intValue() * SECTORSIZE;
	}
	
	public int getSizeInArchive() {
		return this.sizeInArchive.intValue() * SECTORSIZE;
	}
	
	public String getName() throws Exception {
		return new String(this.name, StandardCharsets.ISO_8859_1);
	}
	
	public int getLength() {
		return this.directory.length;
	}
}
