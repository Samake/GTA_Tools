package com.gtasa.binary;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gtasa.container.CBinaryObject;

public class BinaryIPL {
	
	private final int HEADERSIZE = 72;
	private IMGDirectory directory;
	private BinaryIPLHeader header;
	private byte[] bytes;
	private byte[] identifier = new byte[4];
	private String ipl = "";
	private List<CBinaryObject> iplObjects = new ArrayList<CBinaryObject>();
	
	public BinaryIPL(IMGDirectory directory, byte[] content) throws Exception {
		this.directory = directory;
		this.bytes = content;
		
		int index = 0;
		System.arraycopy(this.bytes, index, this.identifier, 0, this.identifier.length);
		index = index + this.identifier.length;
		
		this.header = new BinaryIPLHeader(Arrays.copyOfRange(this.bytes, index, index + HEADERSIZE));

		index = this.header.getItemOffset();
		int blockSize = 40;
		
		if (this.header != null) {
			for (int i = 0; i < this.header.getItemCount(); i++) {
				byte[] objectBlock = Arrays.copyOfRange(this.bytes, index, index + blockSize);
				index = index + blockSize;
				this.iplObjects.add(new CBinaryObject(objectBlock));
			}
		}
		
		buildIPL();
	}
	
	private void buildIPL() {
		this.ipl = this.ipl + "inst";
		
		for (CBinaryObject object : this.iplObjects) {
			this.ipl = this.ipl + object.getModelID() + ", " + 
								"Dummy" + ", " + 
								object.getInterior() + ", " + 
								object.getPosX() + ", " + 
								object.getPosY() + ", " + 
								object.getPosZ() + ", " + 
								object.getRotX() + ", " + 
								object.getRotY() + ", " + 
								object.getRotZ() + ", " + 
								object.getRotW() + ", " + 
								object.getLOD() + "\n";
		}
		
		this.ipl = this.ipl + "end";
	}

	public IMGDirectory getDirectory() {
		return this.directory;
	}
	
	public BinaryIPLHeader getHeader() {
		return this.header;
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
	
	public List<CBinaryObject> getObjects() {
		return this.iplObjects;
	}
}
