package com.kmbt.csa.dav.pojo;

public class MonthlyDataModel extends DataModel{
	

    private String month;
  
	public MonthlyDataModel(){
		super();
	}
	
    public MonthlyDataModel(String companyname, String functionality,String storagetype,
			String year, String month, int usagecount) {
    	super(companyname, functionality, storagetype, year, usagecount);
		this.month = month;
	
	}

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	public String toString() {
		return "LogDataModel [companyname=" + companyname + ", functionality=" + functionality+ ", storagetype="+ storagetype
				+", month="+month+", year="+year+", usagecount=" + usagecount +"]";
	}
}
