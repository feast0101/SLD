package com.kmbt.csa.dav.bo.impl;
/*
Copyright (c) 2014-2015 Konica Minolta
Cloud Services Applications
*/
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kmbt.csa.dav.bo.DataBo;
import com.kmbt.csa.dav.dao.DataDao;
import com.kmbt.csa.dav.model.DBDataModel;
import com.kmbt.csa.dav.pojo.DateDataModel;
import com.kmbt.csa.dav.pojo.LogDataModel;
import com.kmbt.csa.dav.pojo.WEEK;
import com.kmbt.csa.dav.util.DAHandler;
/**
 * @author Kallol Das
 * @Description This service layer servlet handles requests coming from controller and interacts directly with the DAO layer.
 * It acts as an interface between controller and DAO layer of the application.
 * @version 1.0
 * 
 */
@Service("dataBo")
public class DataBoImpl implements DataBo {

	@Autowired
	DataDao dataDao;

	@Autowired
	DAHandler da;

	private static final Logger logger = Logger.getLogger(DataBoImpl.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	private String getMonthName(int monthid) {

		String monthname = null;
		switch (monthid) {
		case 0:
			monthname = "JAN";
			break;
		case 1:
			monthname = "FEB";
			break;
		case 2:
			monthname = "MAR";
			break;
		case 3:
			monthname = "APR";
			break;
		case 4:
			monthname = "MAY";
			break;
		case 5:
			monthname = "JUN";
			break;
		case 6:
			monthname = "JUL";
			break;
		case 7:
			monthname = "AUG";
			break;
		case 8:
			monthname = "SEP";
			break;
		case 9:
			monthname = "OCT";
			break;
		case 10:
			monthname = "NOV";
			break;
		case 11:
			monthname = "DEC";
			break;
		default:
			monthname = "UNKNOWN";
		}
		return monthname;
	}
	/**
	 * 
	 * @Description This method calls getAllData() method of {@link DataDao}
	 *              objects then with the retrieved result {@link List} of
	 *              {@link DBDataModel} creates a new {@link List} of
	 *              {@link LogDataModel} objects
	 * @return {@link List} of {@link LogDataModel} objects
	 */
	public List<LogDataModel> populateLogData() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		List<LogDataModel> list = new ArrayList<LogDataModel>();
		DBDataModel log;
		WEEK wk = null;
		Calendar cal = Calendar.getInstance();
		List<DBDataModel> loglist = dataDao.getAllData();
		Iterator<DBDataModel> it = loglist.iterator();
		while (it.hasNext()) {
			log = it.next();
			logger.debug("usage DB data ==>>" + log);
			cal.setTime(log.getDate());
			int week = cal.get(Calendar.DAY_OF_WEEK);
			for (WEEK type : WEEK.values()) {

				if (type.getWkcode() == week) {
					wk = type;
				}
			}
			Integer year = cal.get(Calendar.YEAR);
			Integer month = cal.get(Calendar.MONTH);
			String date = log.getDate().toString().replaceAll("-", "/");

			list.add(new LogDataModel(log.getCompanyName(), log
					.getFunctionality(), log.getStoragetype(), new Date(sdf
					.format(new Date(date))), wk, getMonthName(month), year
					.toString(), log.getUsageCount()));
		}
	
		logger.info("size of populated log data list :" + list.size());
		return list;
	}

	/**
	 * 
	 * @Description This method calls getSelectiveData(sDate,eDate) method of {@link DataDao}
	 *              objects then with the retrieved result {@link List} of
	 *              {@link DBDataModel} creates a new {@link List} of
	 *              {@link LogDataModel} objects
	 * @param startDate, endDate
	 * @return {@link List} of {@link LogDataModel} objects
	 */
	public List<LogDataModel> populateLogData(Date startDate, Date endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		java.sql.Date sDate=new java.sql.Date(startDate.getTime());
		java.sql.Date eDate=new java.sql.Date(endDate.getTime());
		List<LogDataModel> list = new ArrayList<LogDataModel>();
		DBDataModel log;
		WEEK wk = null;
		Calendar cal = Calendar.getInstance();
		List<DBDataModel> loglist = dataDao.getSelectiveData(sDate,eDate);

		Iterator<DBDataModel> it = loglist.iterator();
		while (it.hasNext()) {
			log = it.next();
			logger.debug("usage DB data ==>>" + log);
			cal.setTime(log.getDate());
			int week = cal.get(Calendar.DAY_OF_WEEK);
			for (WEEK type : WEEK.values()) {

				if (type.getWkcode() == week) {
					wk = type;
				}
			}
			Integer year = cal.get(Calendar.YEAR);
			Integer month = cal.get(Calendar.MONTH);
			String date = log.getDate().toString().replaceAll("-", "/");

			list.add(new LogDataModel(log.getCompanyName(), log
					.getFunctionality(), log.getStoragetype(), new Date(sdf
					.format(new Date(date))), wk, getMonthName(month), year
					.toString(), log.getUsageCount()));
		}
		logger.info("size of populated log data list :" + list.size());
		return list;
	}
	@Override
	public List<LogDataModel> getTenantData(List<LogDataModel> list,
			String companyname) {
		List<LogDataModel> sublist = new ArrayList();
		Iterator<LogDataModel> it = list.iterator();
		LogDataModel ldm = null;
		while (it.hasNext()) {
			ldm = it.next();
			if (ldm.getCompanyname().equalsIgnoreCase(companyname))
				sublist.add(ldm);
		}
		logger.info("size of sub list retrieved :" + sublist.size());
		return sublist;
	}

	@Override
	public List<LogDataModel> getYearData(List<LogDataModel> list, String year) {
		List<LogDataModel> sublist = new ArrayList();
		Iterator<LogDataModel> it = list.iterator();
		LogDataModel ldm = null;
		while (it.hasNext()) {
			ldm = it.next();
			if (ldm.getYear().equalsIgnoreCase(year))
				sublist.add(ldm);
		}
		logger.info("size of year sub list retrieved :" + sublist.size());
		return sublist;
	}
	@Override
	public List<LogDataModel> getDayData(List<LogDataModel> list, String month) {
		List<LogDataModel> sublist = new ArrayList();
		Iterator<LogDataModel> it = list.iterator();
		LogDataModel ldm = null;
		while (it.hasNext()) {
			ldm = it.next();
			if (ldm.getMonth().equalsIgnoreCase(month))
				sublist.add(ldm);
		}
		logger.info("size of month sub list retrieved :" + sublist.size());
		return sublist;
	}
	@Override
	public List<LogDataModel> getMonthData(List<LogDataModel> list, String month) {
		List<LogDataModel> sublist = new ArrayList();
		Iterator<LogDataModel> it = list.iterator();
		LogDataModel ldm = null;
		while (it.hasNext()) {
			ldm = it.next();
			if (ldm.getMonth().equalsIgnoreCase(month))
				sublist.add(ldm);
		}
		logger.info("size of month sub list retrieved :" + sublist.size());
		return sublist;
	}
	@Override
	public List<DBDataModel> getAllData() {
		List<DBDataModel> loglist = dataDao.getAllData();
		return loglist;
	}

}
