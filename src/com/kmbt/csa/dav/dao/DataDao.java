package com.kmbt.csa.dav.dao;

import java.util.List;

import com.kmbt.csa.dav.model.DBDataModel;

public interface DataDao {
	public List<DBDataModel> getAllData();
	public List<DBDataModel> getTenantData(String tenant);
	public List<DBDataModel> getSelectiveData(java.sql.Date startDt,java.sql.Date endDate);
}
