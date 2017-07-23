package com.kmbt.csa.dav.pojo;

public class WeeklyDataModel  extends DataModel{
	
	private WEEK weekcode;
        
    public WeeklyDataModel(){
    	super();
    }
    
    public WeeklyDataModel(String companyname, String functionality,String storagetype,
			String year, WEEK weekcode, int usagecount) {
    	super(companyname, functionality, storagetype, year, usagecount);
		this.weekcode = weekcode;
	}
    
   	public WEEK getWeekcode() {
		return weekcode;
	}
   	
	public void setWeekcode(WEEK weekcode) {
		this.weekcode = weekcode;
	}
	
	public String toString() {
		return "LogDataModel [companyname=" + super.companyname + ", functionality=" + super.functionality+ ", storagetype="+ super.storagetype
				+", weekcode="+weekcode+", year="+super.year+", usagecount=" + super.usagecount +"]";
	}
}
