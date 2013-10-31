package com.gyang.learn.bdb.sample;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class SimpleEntityClass {
	@PrimaryKey
	private String pKey;
	@SecondaryKey(relate=Relationship.MANY_TO_ONE)
	private String sKey;
	
	public String getPKey() {
		return pKey;
	}
	public void setPKey(String pKey) {
		this.pKey = pKey;
	}
	public String getSKey() {
		return sKey;
	}
	public void setSKey(String sKey) {
		this.sKey = sKey;
	}
	
	
}
