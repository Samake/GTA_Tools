package com.gtasa.binary;

import java.util.Arrays;

import com.gtasa.core.FileSystem;
import com.sun.jna.platform.win32.WinDef.DWORD;

public class BinaryIPLHeader {
	
	private byte[] content;
	private DWORD itemCount = new DWORD();
	private DWORD cullCount = new DWORD();
	private DWORD grgeCount = new DWORD();
	private DWORD enexCount = new DWORD();
	private DWORD parkedCars = new DWORD();
	private DWORD pickCount = new DWORD();
	private DWORD itemOffset = new DWORD();
	
	public BinaryIPLHeader(byte[] bytes) {
		this.content = bytes;
		
		int index = 0;
		this.itemCount.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		this.cullCount.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		this.grgeCount.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		this.enexCount.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		this.parkedCars.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		this.pickCount.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		this.itemOffset.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
	}
	
	public byte[] getBytes() {
		return this.content;
	}
	
	public int getItemCount() {
		return this.itemCount.intValue();
	}
	
	public int getCullCount() {
		return this.cullCount.intValue();
	}
	
	public int getGRGECount() {
		return this.grgeCount.intValue();
	}
	
	public int getEnexCount() {
		return this.grgeCount.intValue();
	}
	
	public int getParkedCars() {
		return this.parkedCars.intValue();
	}
	
	public int getPickCount() {
		return this.pickCount.intValue();
	}
	
	public int getItemOffset() {
		return this.itemOffset.intValue();
	}
}
