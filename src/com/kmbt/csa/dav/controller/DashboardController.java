package com.kmbt.csa.dav.controller;

/*
 Copyright (c) 2014-2015 Konica Minolta
 Cloud Services Applications
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kmbt.csa.dav.bo.DataBo;
import com.kmbt.csa.dav.pojo.LogDataModel;
import com.kmbt.csa.dav.util.DAHandler;

/**
 * @author Kallol Das
 * @Description This controller servlet handles all the requests coming from
 *              both Gross report and Company report page
 * @version 1.0
 * 
 */
@Controller
@SessionAttributes("logDataList")
public class DashboardController {

	@Autowired
	DataBo dataservice;

	@Autowired
	DAHandler datahandler;

	private static final Logger logger = Logger
			.getLogger(DashboardController.class);
	private int maxYear = 0;
	private String tenant = "";

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	private int getMaxYear() {
		return maxYear;
	}

	private void setMaxYear(int year) {
		maxYear = year;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/fetchdata.do", method = RequestMethod.POST)
	public ModelAndView fetchData(
			@ModelAttribute("logDataList") List<LogDataModel> lt,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate, Model model,
			HttpSession session) {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date sDate = new Date(sdf.format(new Date(startDate)));
		Date eDate = new Date(sdf.format(new Date(endDate)));
		logger.info("startDate >>" + startDate);
		logger.info("endDate >>" + endDate);
		List<LogDataModel> logDataList = dataservice.populateLogData(sDate,
				eDate);
		logger.info("Setting logdataList.....");
		if (logDataList.isEmpty())
			logDataList = dataservice.populateLogData();
		model.addAttribute("logDataList", logDataList);
		return new ModelAndView("redirect:showHomeDashboard.do");
	}

	@RequestMapping(value = "/fetchdata2.do", method = RequestMethod.GET)
	public ModelAndView fetchData(
			@ModelAttribute("logDataList") List<LogDataModel> lt, Model model,
			HttpSession session) {

		List<LogDataModel> logDataList = dataservice.populateLogData();
		logger.info("Setting logdataList.....");
		model.addAttribute("logDataList", logDataList);
		return new ModelAndView("redirect:showHomeDashboard.do");
	}

	/**
	 * 
	 * @Description This method handles monthly report of gross page and
	 *              interacts with BO class to fetch data collections. It sets
	 *              'logdatafeed' attribute in the {@link Model} object
	 * @return {@link ModelAndView} object
	 */
	@RequestMapping(value = "/showAnalytics.do", method = RequestMethod.GET)
	public ModelAndView showAnalytics(
			@ModelAttribute("logDataList") List<LogDataModel> lt, Model model,
			HttpSession session) {
		String logDataFeed = prepareString(doFetchLogData(lt));
		String dayDataFeed = prepareString(doFetchDayData(lt));
		String motionDataFeed = prepareString(doFetchMotionData(lt));
		model.addAttribute("logdatafeed", logDataFeed);
		model.addAttribute("daydatafeed", dayDataFeed);
		model.addAttribute("motiondatafeed", motionDataFeed);
		return new ModelAndView("Analytics");
	}

	/**
	 * 
	 * @Description This method handles monthly report of gross page and
	 *              interacts with BO class to fetch data collections. It sets
	 *              'logdatafeed' attribute in the {@link Model} object
	 * @return {@link ModelAndView} object
	 */
	@RequestMapping(value = "/showHomeDashboard.do", method = RequestMethod.GET)
	public ModelAndView showHomeDashboard(
			@ModelAttribute("logDataList") List<LogDataModel> lt, Model model,
			HttpSession session) {
		String logDataFeed = prepareString(doFetchLogData(lt));
		String dayDataFeed = prepareString(doFetchDayData(lt));
		String motionDataFeed = prepareString(doFetchMotionData(lt));
		model.addAttribute("logdatafeed", logDataFeed);
		model.addAttribute("daydatafeed", dayDataFeed);
		model.addAttribute("motiondatafeed", motionDataFeed);
		model.addAttribute("maxyear", getMaxYear());

		return new ModelAndView("HomeDashboardpage");
	}

	/**
	 * 
	 * @Description This method handles monthly report of gross page. It filters
	 *              out list based on the year specified and interacts with BO
	 *              class to fetch data collections. It sets
	 *              'logdatafeed',monthdatafeed and storagedatafeed attributes
	 *              in the {@link Model} object
	 * @return {@link ModelAndView} object
	 */
	@RequestMapping(value = "/showHomeDashboard1.do", method = RequestMethod.POST)
	public ModelAndView showHomeDashboard1(
			@ModelAttribute("logDataList") List<LogDataModel> lt,
			@RequestParam(value = "year") String year, Model model,
			HttpSession session) {
		logger.info("year Request Param :" + year);
		List<LogDataModel> list = dataservice.getYearData(lt, year);
		if (list.isEmpty())
			model.addAttribute("noDataErrorMessage",
					"Sufficient data is not available to draw chart .......");
		String dayDataFeed = prepareString(doFetchDayData(list));
		String monthDataFeed = prepareString(doFetchMonthData(list));
		String storageDataFeed = prepareString(doFetchStorageData(list));
		String logDataFeed = prepareString(doFetchLogData(lt));
		model.addAttribute("logdatafeed", logDataFeed);
		model.addAttribute("monthdatafeed", monthDataFeed);
		model.addAttribute("daydatafeed", dayDataFeed);
		model.addAttribute("storagedatafeed", storageDataFeed);
		// model.addAttribute("maxyear", getMaxYear());
		return new ModelAndView("homeMonthSubView");
	}

	/**
	 * 
	 * @Description This method handles monthly report of gross page. It filters
	 *              out list based on the year specified and interacts with BO
	 *              class to fetch data collections. It sets
	 *              'logdatafeed',weekdatafeed and storagedatafeed attributes in
	 *              the {@link Model} object
	 * @return {@link ModelAndView} object
	 */
	@RequestMapping(value = "/showHomeDashboard2.do", method = RequestMethod.POST)
	public ModelAndView showHomeDashboard2(
			@ModelAttribute("logDataList") List<LogDataModel> lt,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month, Model model,
			HttpSession session) {
		logger.info("year Request Param :" + year);
		logger.info("month Request Param :" + month);
		List<LogDataModel> list1 = dataservice.getYearData(lt, year);
		List<LogDataModel> list2 = dataservice.getMonthData(list1, month);

		if (list2.isEmpty())
			model.addAttribute("noDataErrorMessage",
					"Sufficient data is not available to draw chart .......");
		String storageDataFeed = prepareString(doFetchStorageData(list2));
		String weekDataFeed = prepareString(doFetchWeekData(list2));
		String dayDataFeed = prepareString(doFetchDayData(lt));
		String logDataFeed = prepareString(doFetchLogData(lt));
		model.addAttribute("logdatafeed", logDataFeed);
		model.addAttribute("storagedatafeed", storageDataFeed);
		model.addAttribute("weekdatafeed", weekDataFeed);
		model.addAttribute("daydatafeed", dayDataFeed);

		return new ModelAndView("homeWeekSubView");
	}

	/**
	 * 
	 * @Description This method handles monthly report of company page and
	 *              interacts with BO class to fetch data collections. It sets
	 *              'monthdatafeed','weekdatafeed' attribute in the
	 *              {@link Model} object
	 * @return {@link ModelAndView} object
	 */
	@RequestMapping(value = "/showCompanyDashboard.do", method = RequestMethod.GET)
	public ModelAndView showCompanyDashboard(
			@ModelAttribute("logDataList") List<LogDataModel> lt, Model model,
			HttpSession session) {

		String logDataFeed = prepareString(doFetchLogData(lt));
		String monthDataFeed = prepareString(doFetchMonthData(lt));
		String weekDataFeed = prepareString(doFetchWeekData(lt));
		model.addAttribute("logdatafeed", logDataFeed);
		model.addAttribute("monthdatafeed", monthDataFeed);
		model.addAttribute("weekdatafeed", weekDataFeed);
		model.addAttribute("tenantname", getTenant());
		model.addAttribute("maxyear", getMaxYear());
		return new ModelAndView("CompanyDashboard");
	}

	/**
	 * 
	 * @Description This method handles weekly report of company page and
	 *              interacts with BO class to fetch data collections. It sets
	 *              'weekdatafeed' attribute in the {@link Model} object
	 * @return {@link ModelAndView} object
	 */
	@RequestMapping(value = "/generateReportTenant1.do", method = RequestMethod.POST)
	public ModelAndView showCompanyReportTenant1(
			@ModelAttribute("logDataList") List<LogDataModel> lt,
			@RequestParam(value = "tenant") String tenant,
			@RequestParam(value = "year") String year, Model model,
			HttpSession session) {

		List<LogDataModel> list1 = dataservice.getYearData(lt, year);
		logger.info("year Request Param :" + year);
		List<LogDataModel> list2 = dataservice.getTenantData(list1, tenant);
		logger.info("Tenant Request Param :" + tenant);
		if (list2.isEmpty())
			model.addAttribute("noDataErrorMessage",
					"Sufficient data is not available to draw chart .......");
		String monthDataFeed = prepareString(doFetchMonthData(list2));
		model.addAttribute("monthdatafeed", monthDataFeed);
		model.addAttribute("tenantname", getTenant());
		model.addAttribute("maxyear", getMaxYear());
		return new ModelAndView("companyMonthSubView");
	}

	/**
	 * 
	 * @Description This method handles weekly report of company page and
	 *              interacts with BO class to fetch data collections. It sets
	 *              'weekdatafeed' attribute in the {@link Model} object
	 * @return {@link ModelAndView} object
	 */
	@RequestMapping(value = "/generateReportTenant2.do", method = RequestMethod.POST)
	public ModelAndView showCompanyReportTenant2(
			@ModelAttribute("logDataList") List<LogDataModel> lt,
			@RequestParam(value = "tenant") String tenant,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month, Model model,
			HttpSession session) {

		List<LogDataModel> list1 = dataservice.getYearData(lt, year);
		List<LogDataModel> list2 = dataservice.getMonthData(list1, month);
		logger.info("year Request Param :" + year);
		logger.info("month Request Param :" + month);
		List<LogDataModel> list3 = dataservice.getTenantData(list2, tenant);
		logger.info("Tenant Request Param :" + tenant);
		if (list3.isEmpty())
			model.addAttribute("noDataErrorMessage",
					"Sufficient data is not available to draw chart .......");
		String weekDataFeed = prepareString(doFetchWeekData(list3));
		model.addAttribute("weekdatafeed", weekDataFeed);
		model.addAttribute("tenantname", getTenant());
		model.addAttribute("maxyear", getMaxYear());
		return new ModelAndView("companyWeekSubView");
	}

	public String prepareString(String[] l) {

		StringBuilder sb = new StringBuilder("[");

		for (String s : l) {
			logger.debug("datafeed retrieved >>" + s);
			if (s == null)
				continue;
			sb.append(s);
		}
		sb.append("]");
		String datafeed = sb.toString();
		logger.debug("datafeed :" + datafeed);
		return datafeed;
	}

	/**
	 * 
	 * @Description This method interacts with BO class to fetch raw data
	 *              collections
	 * @return Array of {@link String} objects
	 */
	public String[] doFetchWeekData(List<LogDataModel> lt) {
		return datahandler.getWeekData(lt);
	}

	/**
	 * 
	 * @Description This method interacts with BO class to fetch raw data
	 *              collections
	 * @return Array of {@link String} objects
	 */
	public String[] doFetchMonthData(List<LogDataModel> lt) {
		return datahandler.getMonthData(lt);
	}

	/**
	 * 
	 * @Description This method interacts with BO class to fetch raw data
	 *              collections
	 * @return Array of {@link String} objects
	 */
	public String[] doFetchDayData(List<LogDataModel> lt) {
		return datahandler.getDayData(lt);
	}

	/**
	 * 
	 * @Description This method interacts with BO class to fetch raw data
	 *              collections
	 * @return Array of {@link String} objects
	 */
	public String[] doFetchStorageData(List<LogDataModel> lt) {
		return datahandler.getStorageData(lt);
	}

	/**
	 * 
	 * @Description This method generates String array representation of fetched
	 *              raw data
	 * @return Array of {@link String} objects
	 */
	public String[] doFetchLogData(List<LogDataModel> lt) {
		Iterator<LogDataModel> it;
		LogDataModel ldm;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		int i = 0;

		String[] logDataFeed = new String[lt.size() + 1];
		logger.info("log data feed length :" + logDataFeed.length);
		if (!lt.isEmpty()) {
			logDataFeed[i] = new String(
					"['Company Name', 'Functionality','Storage Type', 'Date','Month','Week','Year','Usage Count'],");
			it = lt.iterator();
			while (it.hasNext()) {
				ldm = it.next();
				i++;

				logDataFeed[i] = "['" + ldm.getCompanyname() + "','"
						+ ldm.getFunctionality() + "','" + ldm.getStoragetype()
						+ "',new Date('" + sdf.format(ldm.getDate()) + "'),'"
						+ ldm.getMonth() + "','" + ldm.getWeekcode() + "','"
						+ ldm.getYear() + "'," + ldm.getUsagecount() + "],";
				setMaxYear((Integer.valueOf(ldm.getYear()) > getMaxYear()) ? Integer
						.valueOf(ldm.getYear()) : getMaxYear());
				setTenant(ldm.getCompanyname());
			}
			logDataFeed[i] = logDataFeed[i].substring(0,
					logDataFeed[i].length() - 1);
		}
		return logDataFeed;

	}

	/**
	 * 
	 * @Description This method generates String array representation for Motion
	 *              chart data
	 * @return Array of {@link String} objects
	 */
	public String[] doFetchMotionData(List<LogDataModel> lt) {
		Iterator<LogDataModel> it;
		LogDataModel ldm;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		int i = 0;

		String[] logDataFeed = new String[lt.size() + 1];
		logger.info("motion data feed length :" + (logDataFeed.length + 1));
		if (!lt.isEmpty()) {
			logDataFeed[i] = new String(
					"['Company Name', 'Date', 'Functionality','Storage Type','Month','Year','Usage Count'],");
			it = lt.iterator();
			while (it.hasNext()) {
				ldm = it.next();
				i++;

				logDataFeed[i] = "['" + ldm.getCompanyname() + "',new Date('"
						+ sdf.format(ldm.getDate()) + "'),'"
						+ ldm.getFunctionality() + "','" + ldm.getStoragetype()
						+ "','" + ldm.getMonth() + "',"
						+ Integer.getInteger(ldm.getYear()) + ","
						+ ldm.getUsagecount() + "],";
			}
			logDataFeed[i] = logDataFeed[i].substring(0,
					logDataFeed[i].length() - 1);
		}
		return logDataFeed;

	}
}
