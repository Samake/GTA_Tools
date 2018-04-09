package com.gtasa.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gtasa.core.FileSystem;

public class GTA3IMG {
	
	private final int DIRECTORYSIZE = 32;
	
	private byte[] img;
	private GTA3IMGHeader header;
	private List<GTA3IMGBinaryIPL> iplList = new ArrayList<GTA3IMGBinaryIPL>();
	
	public GTA3IMG(byte[] content) throws Exception {
		this.img = FileSystem.sortBytes(content, 0, content.length);

		this.header = new GTA3IMGHeader(Arrays.copyOfRange(this.img, 0, 8));
		
		if (this.header != null) {
			decompile();
		}
	}
	
	private void decompile() throws Exception {
		if (this.header != null) {
			int index = this.header.getLength();
			
			for (int i = 0; i < this.header.getEntries(); i++) {
				GTA3IMGDirectory directory = new GTA3IMGDirectory(Arrays.copyOfRange(this.img, index, index + DIRECTORYSIZE));
				index = index + DIRECTORYSIZE;

				if (directory.getName().contains(".ipl")) {
					this.iplList.add(new GTA3IMGBinaryIPL(directory, Arrays.copyOfRange(this.img, directory.getOffset(), directory.getOffset() + directory.getStreamingSize())));
				}
			}
		}
	}
	
	public byte[] getBytes() {
		return this.img;
	}
	
	public GTA3IMGHeader getHeader() {
		return this.header;
	}
	
	public List<GTA3IMGBinaryIPL> getIPLs() {
		return this.iplList;
	}
	
	public int getLength() {
		return this.img.length;
	}
}
