package com.gyang.learn.bdb.persist.gettingStarted;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Vendor {
	@PrimaryKey
	private String vendor;
	private String address;
	private String bizPhoneNumber;
	private String city;
	private String repName;
	private String repPhoneNumber;
	private String state;
	private String zipCode;
	
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBizPhoneNumber() {
		return bizPhoneNumber;
	}
	public void setBizPhoneNumber(String bizPhoneNumber) {
		this.bizPhoneNumber = bizPhoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getRepPhoneNumber() {
		return repPhoneNumber;
	}
	public void setRepPhoneNumber(String repPhoneNumber) {
		this.repPhoneNumber = repPhoneNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
