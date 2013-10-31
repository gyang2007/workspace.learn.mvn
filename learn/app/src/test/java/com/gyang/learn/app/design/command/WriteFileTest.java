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

public class WriteFileTest {
	private static int threadIndex = 0;
	
	public WriteFileTest() {
		
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
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long d1 = System.currentTimeMillis();
		WriteFileTest test = new WriteFileTest();
		
		List<ICommand> commands = new ArrayList<ICommand>();
		List<Future<String>> computes = new ArrayList<Future<String>>();
		List<Future<Object>> writeFiles = new ArrayList<Future<Object>>();
		
		ExecutorService computeService = Executors.newFixedThreadPool(5);
		ExecutorService writeService = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 10; i++) {
			computes.add(computeService.submit(new ThreadTest(threadIndex++)));
		}
		computeService.shutdown();
		
		Iterator<Future<String>> computesIterator;
		Future<String> computeFuture;
		Iterator<Future<Object>> writeFilesIterator;
		Future<Object> writeFileFuture;
		while(true) {
			if(computes.size() == 0 && commands.size() == 0) {
				break;
			}
			
			computesIterator = computes.iterator();
			while(computesIterator.hasNext()) {
				computeFuture = computesIterator.next();
				if(computeFuture.isDone()) {
					writeFiles.add(writeService.submit(new WriteFileCommond(new WriteFile(computeFuture.get(), test.getWriteFile()))));
					computesIterator.remove();
				}
			}
			
			writeFilesIterator = writeFiles.iterator();
			while(writeFilesIterator.hasNext()) {
				writeFileFuture = writeFilesIterator.next();
				if(writeFileFuture.isDone()) {
					System.out.println(writeFileFuture.get() + " done.");
					writeFilesIterator.remove();
				}
			}
		}
		
		writeService.shutdown();
		long d2 = System.currentTimeMillis();
		System.out.println("Multi Thread : " + (d2 - d1));
	}
}

class ThreadTest implements Callable<String> {
	private String str = "ahoshdfoahsoidhfpaosihdfoheahisdfaoishdoifha";
	private int index;
	public ThreadTest(int index) {
		this.index = index;
	}
	public String call() throws Exception {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 100000; i++) {
			sb.append(str).append("\n");
//			System.out.println(this.index + " : compute...");
			Thread.sleep(1 * 5);
		}
		
		return sb.toString();
	}
}
