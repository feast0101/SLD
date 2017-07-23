package com.kmbt.csa.dav.pojo;

import java.util.Date;
/*
Copyright (c) 2013-2014 Konica Minolta
Cloud Services Applications
*/

/**
* @author Kallol Das
* @Description This abstract pojo class has getter and setter methods for all the corresponding common variables
* used in other data model classes
* @version 1.0
* 
*/
public class DataModel {

	protected String companyname;
	protected String functionality;
	protected String storagetype;
	protected String year;
	protected int usagecount;

	public DataModel(String companyname, String functionality,String storagetype, String year,int usagecount) {

		this.companyname = companyname;
		this.functionality = functionality;
		this.storagetype =storagetype;
		this.year = year;
		this.usagecount = usagecount;
	}

	public DataModel() {
		super();
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getFunctionality() {
		return functionality;
	}

	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	public String getStoragetype() {
		return storagetype;
	}

	public void setStoragetype(String storagetype) {
		this.storagetype = storagetype;
	}

	public int getUsagecount() {
		return usagecount;
	}

	public void setUsagecount(int usagecount) {
		this.usagecount = usagecount;
	}

}
