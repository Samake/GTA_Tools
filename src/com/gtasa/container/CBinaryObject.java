package com.gtasa.container;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import com.sun.jna.platform.win32.WinDef.DWORD;

public class CBinaryObject {
	
	private final int VALUESIZE = 4;
	private byte[] content;
	private byte[] posX;
	private byte[] posY;
	private byte[] posZ;
	private byte[] rotX;
	private byte[] rotY;
	private byte[] rotZ;
	private byte[] rotW;
	private byte[] modelID;
	private byte[] interior;
	private byte[] lod;
	
	public CBinaryObject(byte[] bytes) {
		this.content = bytes;
		
		int index = 0;
		this.posX = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.posY = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.posZ = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.rotX = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.rotY = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.rotZ = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.rotW = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.modelID = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.interior = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
		index = index + DWORD.SIZE;
		
		this.lod = Arrays.copyOfRange(this.content, index, index + VALUESIZE);
	}
	
	public byte[] getBytes() {
		return this.content;
	}
	
	public float getPosX() {
		return ByteBuffer.wrap(this.posX).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public float getPosY() {
		return ByteBuffer.wrap(this.posY).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public float getPosZ() {
		return ByteBuffer.wrap(this.posZ).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public float getRotX() {
		return ByteBuffer.wrap(this.rotX).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public float getRotY() {
		return ByteBuffer.wrap(this.rotY).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public float getRotZ() {
		return ByteBuffer.wrap(this.rotZ).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public float getRotW() {
		return ByteBuffer.wrap(this.rotW).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public int getModelID() {
		return ByteBuffer.wrap(this.modelID).order(ByteOrder.LITTLE_ENDIAN).getInt();
	}
	
	public int getInterior() {
		return ByteBuffer.wrap(this.interior).order(ByteOrder.LITTLE_ENDIAN).getInt();
	}
	
	public int getLOD() {
		return ByteBuffer.wrap(this.lod).order(ByteOrder.LITTLE_ENDIAN).getInt();
	}
}
