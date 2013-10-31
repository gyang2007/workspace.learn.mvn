package com.gyang.learn.app.design.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	private String str;
	private File path;
	
	public WriteFile(String str, File path) {
		this.str = str;
		this.path = path;
	}
	
	public File getFile() {
		return path;
	}
	
	public void write() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(this.path);
			fw.write(this.str);
		} catch (IOException e) {
			throw new RuntimeException("bad file", e);
		} finally{
			if(fw != null) {
				try {
					fw.close();
					fw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
