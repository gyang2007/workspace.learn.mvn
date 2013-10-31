package com.gyang.learn.bdb.sample;

import java.io.File;
import java.util.Iterator;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import com.sleepycat.persist.StoreConfig;

public class SimpleStoreGet {
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
		
		SimpleEntityClass s1 = sda.pIdx.get("keyone:2");
		System.out.println(s1.getSKey());
		
		this.shutdown();
	}
	
	private void run2() throws DatabaseException{
		this.setup();
		
		sda = new SimpleDA(store);
		EntityCursor<SimpleEntityClass> eCursor = sda.pIdx.entities();
		SimpleEntityClass s = eCursor.next();
		System.out.println(s.getSKey());
		System.out.println(eCursor.count());
		
/*		Iterator<SimpleEntityClass> iterator = eCursor.iterator();
		int count = 0;
		while(iterator.hasNext()){
			System.out.println(iterator.next().getSKey());
			if(count++ == 50){
				break;
			}
		}*/
		
		eCursor.close();
		
		this.shutdown();
	}
	
	private void run3() throws DatabaseException{
		this.setup();
		
		PrimaryIndex<String, SimpleEntityClass> pi = store.getPrimaryIndex(String.class, SimpleEntityClass.class);
		SecondaryIndex<String, String, SimpleEntityClass> si = store.getSecondaryIndex(pi, String.class, "sKey");
		EntityCursor<SimpleEntityClass> eCursor = si.subIndex("skeyone:0").entities();
/*		Iterator<SimpleEntityClass> iterator = eCursor.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().getPKey());
		}
		System.out.println(eCursor.count());*/
		
		for(SimpleEntityClass s = eCursor.next(); s != null; s = eCursor.next()){
			System.out.println(s.getPKey());
		}
		System.out.println(eCursor.count());
		
		eCursor.close();
		this.shutdown();
	}
	
	private void run4() throws DatabaseException{
		this.setup();
		
		PrimaryIndex<String, SimpleEntityClass> pIndex = store.getPrimaryIndex(String.class, SimpleEntityClass.class);
		EntityCursor<SimpleEntityClass> eCursor = pIndex.entities("keyone:5", true, "keyone:73", true);
		for(SimpleEntityClass s = eCursor.next(); s != null; s = eCursor.next()){
			System.out.println(s.getPKey() + ":" + s.getSKey());
		}
		
		eCursor.close();
		this.shutdown();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleStoreGet ssg = new SimpleStoreGet();
//		ssg.run();
//		ssg.run2();
//		ssg.run3();
		ssg.run4();
	}

}
