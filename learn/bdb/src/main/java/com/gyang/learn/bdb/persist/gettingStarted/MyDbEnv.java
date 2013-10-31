package com.gyang.learn.bdb.persist.gettingStarted;

import java.io.File;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

public class MyDbEnv {

	private Environment myEnv;
	private EntityStore store;
	
	public MyDbEnv() {
		
	}
	
	public void setup(File envHome, boolean readOnly){
		EnvironmentConfig config = new EnvironmentConfig();
		config.setReadOnly(readOnly);
		config.setAllowCreate(!readOnly);
		
		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setReadOnly(readOnly);
		storeConfig.setAllowCreate(!readOnly);
		
		this.myEnv = new Environment(envHome, config);
		this.store = new EntityStore(myEnv, "StoreEntity", storeConfig);
	}

	public Environment getMyEnv() {
		return myEnv;
	}

	public EntityStore getStore() {
		return store;
	}
	
	public void close(){
		if(this.store != null){
			this.store.close();
			this.store = null;
		}
		
		if(this.myEnv != null){
			this.myEnv.close();
			this.myEnv = null;
		}
	}
}
