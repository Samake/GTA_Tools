package com.gtasa.binary;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gtasa.core.FileSystem;
import com.gtasa.gui.GUIConsole;

public class IMG {
	
	private final int DIRECTORYSIZE = 32;
	
	private byte[] img;
	private IMGHeader header;
	private List<BinaryIPL> iplList = new ArrayList<BinaryIPL>();
	
	public IMG(byte[] content) throws Exception {
		this.img = FileSystem.sortBytes(content, 0, content.length);

		this.header = new IMGHeader(Arrays.copyOfRange(this.img, 0, 8));
		
		if (this.header != null) {
			GUIConsole.output("Buffering binary ipl files from gta3.img");
			
			decompile();
			GUIConsole.output(this.iplList.size() + " IPL files were decompiled");
			
		}
	}
	
	private void decompile() throws Exception {
		if (this.header != null) {
			int index = this.header.getLength();
			
			for (int i = 0; i < this.header.getEntries(); i++) {
				IMGDirectory directory = new IMGDirectory(Arrays.copyOfRange(this.img, index, index + DIRECTORYSIZE));
				index = index + DIRECTORYSIZE;

				if (directory.getName().contains(".ipl")) {
					byte[] iplFile = Arrays.copyOfRange(this.img, directory.getOffset(), directory.getOffset() + directory.getStreamingSize());
					
					if (new String(Arrays.copyOfRange(iplFile, 0, 4), StandardCharsets.ISO_8859_1).equals("bnry")) {
						this.iplList.add(new BinaryIPL(directory, iplFile));
					}
				}
			}
		}
	}
	
	public byte[] getBytes() {
		return this.img;
	}
	
	public IMGHeader getHeader() {
		return this.header;
	}
	
	public List<BinaryIPL> getIPLs() {
		return this.iplList;
	}
	
	public int getLength() {
		return this.img.length;
	}
}
