package com.gyang.learn.app.design.command;

import java.util.concurrent.Callable;

public class WriteFileCommond implements ICommand, Callable<Object>{
	private WriteFile writeFile;
	
	public WriteFileCommond(WriteFile writeFile) {
		this.writeFile = writeFile;
	}
	
	public void execute() {
		this.writeFile.write();
	}

	public Object call() throws Exception {
		this.execute();
		return this.writeFile.getFile().getAbsolutePath();
	}

}
