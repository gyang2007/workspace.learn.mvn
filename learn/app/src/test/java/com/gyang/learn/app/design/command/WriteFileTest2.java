package com.gyang.learn.app.design.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WriteFileTest2 {
	private static int threadIndex = 0;
	
	public WriteFileTest2() {
		
	}
	
	private int fileIndex = 0;
	private synchronized File getWriteFile() {
		String path = "/home/gyang/share/testiodata/" + fileIndex++ + ".log";
		File newFile = new File(path);
		if(!newFile.exists()) {
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return newFile;
	}
	
	private String getStr() throws InterruptedException {
		String str = "ahoshdfoahsoidhfpaosihdfoheahisdfaoishdoifha";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1000; i++) {
			sb.append(str).append("\n");
			Thread.sleep(1 * 10);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long d1 = System.currentTimeMillis();
		WriteFileTest2 test = new WriteFileTest2();
		
		for(int i = 0; i < 10; i++) {
			WriteFileCommond writeFileCommond = new WriteFileCommond(new WriteFile(test.getStr(), test.getWriteFile()));
			writeFileCommond.execute();
		}
		long d2 = System.currentTimeMillis();
		System.out.println("Common : " + (d2 - d1));
	}
}
