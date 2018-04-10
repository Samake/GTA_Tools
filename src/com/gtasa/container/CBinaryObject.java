package com.gtasa.container;

import java.util.Arrays;

import com.gtasa.core.FileSystem;
import com.sun.jna.platform.win32.WinDef.DWORD;

public class CBinaryObject extends CObject {
	
	private byte[] content;
	private DWORD posX = new DWORD();
	
	public CBinaryObject(byte[] bytes) {
		this.content = bytes;
		
		int index = 0;
		this.posX.setValue(FileSystem.readUInt(Arrays.copyOfRange(this.content, index, index + DWORD.SIZE)));
		index = index + DWORD.SIZE;
		
		System.err.println(	"posX: " + getPosX()
		
		);
	}
	
	public byte[] getBytes() {
		return this.content;
	}
	
	public float getPosX() {
		return this.posX.floatValue();
	}
}
