package com.kmbt.csa.dav.pojo;

import java.util.Date;

public class DateDataModel extends DataModel {
    private Date date;
	public DateDataModel(){
		super();
	}
	
    public DateDataModel(String companyname, String functionality, String storagetype, String year, Date date, int usagecount) {
		super(companyname, functionality, storagetype, year, usagecount);
		this.date = date;
		
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String toString() {
		return "LogDataModel [companyname=" + companyname + ", functionality=" + functionality+ ", storagetype="+ storagetype
				+ ", date=" + date +", year="+year+", usagecount=" + usagecount +"]";
	}
}
