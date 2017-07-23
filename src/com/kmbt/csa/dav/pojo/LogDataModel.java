package com.kmbt.csa.dav.pojo;

import java.util.Date;


public class LogDataModel implements java.io.Serializable{

	private static final long serialVersionUID = 1246231032506460411L;
	private String companyname;
	private String functionality;
	private String storagetype;

	private Date date;
	private WEEK weekcode;
	private String month;
	private String year;
	private int usagecount;

	public LogDataModel() {
	}

	public LogDataModel(String companyname, String functionality, String storagetype, Date date,
			WEEK weekcode, String month, String year, int usagecount) {
		super();
		this.companyname = companyname;
		this.functionality = functionality;
		this.storagetype =storagetype;
		this.date = date;
		this.weekcode = weekcode;
		this.month = month;
		this.year = year;
		this.usagecount = usagecount;
	}

	public WEEK getWeekcode() {
		return weekcode;
	}

	public void setWeekcode(WEEK weekcode) {
		this.weekcode = weekcode;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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
	public String getStoragetype() {
		return storagetype;
	}

	public void setStoragetype(String storagetype) {
		this.storagetype = storagetype;
	}
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUsagecount() {
		return usagecount;
	}

	public void setUsagecount(int usagecount) {
		this.usagecount = usagecount;
	}
	
	public String toString() {
		return "LogDataModel [companyname=" + companyname + ", functionality=" + functionality + ", storagetype="+storagetype
				+ ", date=" + date +", weekcode="+weekcode.getWkcode()+", month="+month+", year="+year+", usagecount=" + usagecount +"]";
	}
}
