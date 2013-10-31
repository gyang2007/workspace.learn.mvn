package com.gyang.learn.bdb.persist.gettingStarted;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class Inventory {
	@PrimaryKey
	private String sku;
	@SecondaryKey(relate=Relationship.MANY_TO_ONE)
	private String itemName;
	private String category;
	private String vendor;
	private int vendorInventory;
	private float vendorPrice;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public int getVendorInventory() {
		return vendorInventory;
	}
	public void setVendorInventory(int vendorInventory) {
		this.vendorInventory = vendorInventory;
	}
	public float getVendorPrice() {
		return vendorPrice;
	}
	public void setVendorPrice(float vendorPrice) {
		this.vendorPrice = vendorPrice;
	}
}
