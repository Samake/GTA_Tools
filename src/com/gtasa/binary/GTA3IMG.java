package com.gtasa.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GTA3IMG {
	
	private final int DIRECTORYSIZE = 32;
	private final int PLACEMENTHEADERSIZE = 32;
	
	private byte[] img;
	private GTA3IMGHeader header;
	
	private List<GTA3IMGDirectory> directoriesList = new ArrayList<GTA3IMGDirectory>();
	private List<GTA3IMGBinaryIPL> iplList = new ArrayList<GTA3IMGBinaryIPL>();
	
	public GTA3IMG(byte[] content) throws Exception {
		this.img = content;
		
		this.header = new GTA3IMGHeader(Arrays.copyOfRange(this.img, 0, 8));
		
		if (this.header != null) {
			decompile();
		}
	}
	
	private void decompile() throws Exception {
		if (header != null) {
			int index = this.header.getLength();
			
			for (int i = 0; i < this.header.getEntries(); i++) {
				GTA3IMGDirectory directory = new GTA3IMGDirectory(Arrays.copyOfRange(this.img, index, index + DIRECTORYSIZE));
				index = index + DIRECTORYSIZE;

				if (directory.getName().contains(".ipl")) {
					this.directoriesList.add(directory);
				}
			}
			
			for (GTA3IMGDirectory directory : this.directoriesList) {
				this.iplList.add(new GTA3IMGBinaryIPL(Arrays.copyOfRange(this.img, directory.getOffset() + PLACEMENTHEADERSIZE, PLACEMENTHEADERSIZE + directory.getOffset() + directory.getStreamingSize())));
			}
		}
	}
	
	public byte[] getBytes() {
		return this.img;
	}
	
	public GTA3IMGHeader getHeader() {
		return this.header;
	}
	
	public List<GTA3IMGDirectory> getDirectories() {
		return this.directoriesList;
	}
	
	public List<GTA3IMGBinaryIPL> getIPLs() {
		return this.iplList;
	}
	
	public int getLength() {
		return this.img.length;
	}
}
