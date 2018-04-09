package com.gtasa.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class FileSystem {
	
	public static File[] readLibray(String path) {
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		return listOfFiles;
	}
	
	public static String openIPL(String path) throws IOException {
		File file = new File(path);
		
		if (file.exists() && file.isFile()) {
			
			byte[] bytes = new byte[(int)file.length()];
			
			DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
			
			dataInputStream.readFully(bytes);           
			dataInputStream.close();            
			
			return new String(bytes);
		}
		
		return null;
	}
	
	public static byte[] sortBytes(byte[] data, int offset, int length) {
		ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
	    return buffer.get(buffer.array(), offset, length).array();
	}
	
	public static long readUInt(byte[] data) {
	    return ByteBuffer.wrap(data)
	        .order(ByteOrder.LITTLE_ENDIAN)
	        .getInt() & 0xFF_FF_FF_FFL;
	}
	
	public static short readShort(byte[] data) {
	    return ByteBuffer.wrap(data).getShort();
	}
	
	public static byte[] charsToBytes(char[] chars){
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(CharBuffer.wrap(chars));
        return Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
    }

    public static char[] bytesToChars(byte[] bytes){
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(ByteBuffer.wrap(bytes));
        return Arrays.copyOf(charBuffer.array(), charBuffer.limit());    
    }
}
