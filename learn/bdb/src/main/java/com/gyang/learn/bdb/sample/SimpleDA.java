package com.gyang.learn.bdb.sample;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;

public class SimpleDA {
	PrimaryIndex<String, SimpleEntityClass> pIdx;
	SecondaryIndex<String, String, SimpleEntityClass> sIdx;
	
	public SimpleDA(EntityStore store) throws DatabaseException {
		this.pIdx = store.getPrimaryIndex(String.class, SimpleEntityClass.class);
		this.sIdx = store.getSecondaryIndex(this.pIdx, String.class, "sKey");
	}
}
