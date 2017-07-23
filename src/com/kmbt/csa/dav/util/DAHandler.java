package com.kmbt.csa.dav.util;

/*
 Copyright (c) 2014-2015 Konica Minolta
 Cloud Services Applications
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kmbt.csa.dav.controller.DashboardController;
import com.kmbt.csa.dav.pojo.DateDataModel;
import com.kmbt.csa.dav.pojo.LogDataModel;
import com.kmbt.csa.dav.pojo.MonthlyDataModel;
import com.kmbt.csa.dav.pojo.WeeklyDataModel;

/**
 * @author Kallol Das
 * @Description This controller servlet handles all the requests coming from the
 *              DA module
 * @version 1.0
 * 
 */
@Controller
@Service("datahandler")
@SessionAttributes("logDataList")
public class DAHandler {

	@Autowired
	DataProcessor dp;
	
	private List<LogDataModel> data;

	private void setData(List<LogDataModel> data) {
		this.data = data;
	}

	private List<LogDataModel> getData() {
		return data;
	}

	public DAHandler(List<LogDataModel> data) {
		this.data = data;
	}

	/**
	 * 
	 * @Description This constructor calls populateLogData() method of
	 *              {@link DashboardController} to populate a {@link List} of
	 *              {@link LogDataModel} objects
	 */
	public DAHandler() {
		super();
	}

	/**
	 * 
	 * @Description This method searches the entire {@link List} of
	 *              {@link LogDataModel} objects and then with the retrieved
	 *              result creates a new {@link List} of {@link DateDataModel}
	 *              objects
	 * @param {@link String} Tenant Name
	 * @return {@link List} of {@link DateDataModel} objects
	 */
	public List<DateDataModel> getTenantData(String companyname) {
		List<DateDataModel> list = new ArrayList<DateDataModel>();
		LogDataModel log = new LogDataModel();
		List<LogDataModel> loglist = getData();// dc.generateReport(companyname);
		Iterator<LogDataModel> it = loglist.iterator();
		while (it.hasNext()) {
			log = it.next();
			list.add(new DateDataModel(log.getCompanyname(), log
					.getFunctionality(), log.getStoragetype(), log.getYear(),
					log.getDate(), log.getUsagecount()));
		}
		return list;
	}
	/**
	 * 
	 * @Description This method searches the entire {@link List} of
	 *              {@link LogDataModel} objects and then with the retrieved
	 *              result creates a new {@link List} of {@link DateDataModel}
	 *              objects
	 * @param {@link String} Tenant Name
	 * @return {@link List} of {@link DateDataModel} objects
	 */
	public List<DateDataModel> getStorageData(String companyname) {
		List<DateDataModel> list = new ArrayList<DateDataModel>();
		LogDataModel log = new LogDataModel();
		List<LogDataModel> loglist = getData();// dc.generateReport(companyname);
		Iterator<LogDataModel> it = loglist.iterator();
		while (it.hasNext()) {
			log = it.next();
			list.add(new DateDataModel(log.getCompanyname(), log
					.getFunctionality(), log.getStoragetype(), log.getYear(),
					log.getDate(), log.getUsagecount()));
		}
		return list;
	}
	/**
	 * 
	 * @Description This method iterates through the {@link List} of
	 *              {@link LogDataModel} objects and then converts
	 *              {@link LogDataModel} object to {@link DateDataModel} object
	 *              and put then to the {@link List} once again.
	 * @return {@link List} of {@link DateDataModel} objects
	 */
	public List<DateDataModel> getAllData() {
		List<DateDataModel> list = new ArrayList<DateDataModel>();
		LogDataModel log = new LogDataModel();
		List<LogDataModel> loglist = getData();
		Iterator<LogDataModel> it = loglist.iterator();
		while (it.hasNext()) {
			log = it.next();
			list.add(new DateDataModel(log.getCompanyname(), log
					.getFunctionality(), log.getStoragetype(), log.getYear(),
					log.getDate(), log.getUsagecount()));
		}
		return list;
	}

	/**
	 * 
	 * @Description This method iterates through the {@link List} of
	 *              {@link LogDataModel} objects and then converts
	 *              {@link LogDataModel} object to {@link MonthlyDataModel}
	 *              object and put then to the {@link List} once again.
	 * @return {@link List} of {@link MonthlyDataModel} objects
	 */
	public String[] getDayData(@ModelAttribute("logDataList") List<LogDataModel> lt) {
		String[] list = dp.doDayTotal(lt);
		return list;
	}
	/**
	 * 
	 * @Description This method iterates through the {@link List} of
	 *              {@link LogDataModel} objects and then converts
	 *              {@link LogDataModel} object to {@link MonthlyDataModel}
	 *              object and put then to the {@link List} once again.
	 * @return {@link List} of {@link MonthlyDataModel} objects
	 */
	public String[] getMonthData(@ModelAttribute("logDataList") List<LogDataModel> lt) {
		String[] list = dp.doMonthlyTotal(lt);
		return list;
	}
	/**
	 * 
	 * @Description This method iterates through the {@link List} of
	 *              {@link LogDataModel} objects and then converts
	 *              {@link LogDataModel} object to {@link MonthlyDataModel}
	 *              object and put then to the {@link List} once again.
	 * @return {@link List} of {@link MonthlyDataModel} objects
	 */
	public String[] getWeekData(@ModelAttribute("logDataList") List<LogDataModel> lt) {
		String[] list = dp.doWeeklyTotal(lt);
		return list;
	}
	
	/**
	 * 
	 * @Description This method iterates through the {@link List} of
	 *              {@link LogDataModel} objects and then converts
	 *              {@link LogDataModel} object to {@link MonthlyDataModel}
	 *              object and put then to the {@link List} once again.
	 * @return {@link List} of {@link MonthlyDataModel} objects
	 */
	@RequestMapping(value = "/sjson.do", method = RequestMethod.GET )
	@ResponseBody
	public String[] getStorageData(@ModelAttribute("logDataList") List<LogDataModel> lt) {
	   String[] list = dp.doMonthlyStorageTotal(lt);
	   return list;
	}
	/**
	 * 
	 * @Description This method iterates through the {@link List} of
	 *              {@link LogDataModel} objects and then converts
	 *              {@link LogDataModel} object to {@link WeeklyDataModel}
	 *              object and put then to the {@link List} once again.
	 * @return {@link List} of {@link WeeklyDataModel} objects
	 */
	public List<WeeklyDataModel> getWeekData() {
		List<WeeklyDataModel> list = new ArrayList<WeeklyDataModel>();
		LogDataModel log = new LogDataModel();
		List<LogDataModel> loglist = getData();
		Iterator<LogDataModel> it = loglist.iterator();
		while (it.hasNext()) {
			log = it.next();
			list.add(new WeeklyDataModel(log.getCompanyname(), log
					.getFunctionality(), log.getStoragetype(), log.getYear(),
					log.getWeekcode(), log.getUsagecount()));
		}
		return list;
	}
}
