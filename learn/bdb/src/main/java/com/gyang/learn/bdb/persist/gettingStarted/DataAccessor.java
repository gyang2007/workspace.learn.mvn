package com.gyang.learn.bdb.persist.gettingStarted;

import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;

public class DataAccessor {
	private PrimaryIndex<String, Inventory> inventoryPrimaryIndex;
	private SecondaryIndex<String, String, Inventory> inventorySecondaryIndex;
	
	private PrimaryIndex<String, Vendor> vendorPrimaryIndex;
	
	public DataAccessor(EntityStore store) {
		this.inventoryPrimaryIndex = store.getPrimaryIndex(String.class, Inventory.class);
		this.inventorySecondaryIndex = store.getSecondaryIndex(this.inventoryPrimaryIndex, String.class, "itemName");
		
		this.vendorPrimaryIndex = store.getPrimaryIndex(String.class, Vendor.class);
	}

	public PrimaryIndex<String, Inventory> getInventoryPrimaryIndex() {
		return inventoryPrimaryIndex;
	}

	public SecondaryIndex<String, String, Inventory> getInventorySecondaryIndex() {
		return inventorySecondaryIndex;
	}

	public PrimaryIndex<String, Vendor> getVendorPrimaryIndex() {
		return vendorPrimaryIndex;
	}
	
}
