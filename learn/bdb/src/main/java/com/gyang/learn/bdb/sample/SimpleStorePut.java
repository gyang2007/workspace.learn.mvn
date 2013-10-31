package com.gyang.learn.bdb.sample;

import java.io.File;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

public class SimpleStorePut {
	private static File envHome = new File("/home/gyang/develop/app.workspace/bdb.learn/test_resources/JEDB");
	private Environment envmnt;
	private EntityStore store;
	private SimpleDA sda;

	public void setup() throws DatabaseException{
		EnvironmentConfig envConfig = new EnvironmentConfig();
		StoreConfig storeConfig = new StoreConfig();
		
		envConfig.setAllowCreate(true);
		storeConfig.setAllowCreate(true);
		
		// Open the environment and entity store
		envmnt = new Environment(envHome, envConfig);
		store = new EntityStore(envmnt, "EntityStore", storeConfig);
	}
	
	public void shutdown() throws DatabaseException{
		store.close();
		envmnt.close();
	}
	
	private void run() throws DatabaseException{
		this.setup();
		
		sda = new SimpleDA(store);
		
		SimpleEntityClass sec;
		for(int i = 0; i < 100; i++){
			sec = new SimpleEntityClass();
			sec.setPKey("keyone:" + i);
			sec.setSKey("skeyone:" + (i % 5));
			
			sda.pIdx.putNoReturn(sec);
		}
		
		this.shutdown();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleStorePut s = new SimpleStorePut();
		long d1 = System.currentTimeMillis();
		s.run();
		long d2 = System.currentTimeMillis();
		System.out.println("success!!" + (d2 - d1) / 1000);
	}

}
