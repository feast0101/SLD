package com.kmbt.csa.dav.bo;

import java.util.Date;
import java.util.List;

import com.kmbt.csa.dav.model.DBDataModel;
import com.kmbt.csa.dav.pojo.LogDataModel;

public interface DataBo {

	public List<LogDataModel> populateLogData();
	
	public List<LogDataModel> populateLogData( Date startDate, Date endDate);

	public List<LogDataModel> getTenantData(List<LogDataModel> list,String companyname);

	public List<LogDataModel> getYearData(List<LogDataModel> list,String companyname);
	
	public List<LogDataModel> getMonthData(List<LogDataModel> list, String month);
	
	public List<DBDataModel> getAllData();

	public List<LogDataModel> getDayData(List<LogDataModel> list, String month);
}
