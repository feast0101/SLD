package com.kmbt.csa.dav.controller;

/*
 Copyright (c) 2014-2015 Konica Minolta
 Cloud Services Applications
 */

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kmbt.csa.dav.bo.DataBo;
import com.kmbt.csa.dav.model.DBDataModel;
import com.kmbt.csa.dav.pojo.LogDataModel;
import com.kmbt.csa.dav.view.ExcelDataView;

/**
 * @author Kallol Das
 * @Description This controller servlet handles requests coming from raw data
 *              page.
 * @version 1.0
 * 
 */
@Controller
@SessionAttributes("logDataList")
public class DownloadController {

	@Autowired
	DataBo dataservice;

	private static final Logger logger = Logger
			.getLogger(DownloadController.class);

	@RequestMapping(value = "/json.do", method = RequestMethod.GET)
	@ResponseBody
	public List<LogDataModel> drawChart(
			@ModelAttribute("logDataList") List<LogDataModel> lt) {
		return lt;
	}

	/**
	 * 
	 * @Description This method handles the request to generate raw data page
	 * @return String view name
	 */
	@RequestMapping(value = "/getdata.do", method = RequestMethod.GET)
	public String initForm(ModelMap model, HttpSession session) {

		StringBuilder sb = new StringBuilder("[");
		String[] l = doFetchData();
		for (String s : l) {
			logger.debug("datafeed retrieved >>" + s);
			sb.append(s);
		}
		sb.append("]");
		String datafeed = sb.toString();
		logger.debug("datafeed :" + datafeed);

		model.addAttribute("datafeed", datafeed);
		return "RawDataKMDashBoard";
	}

	/**
	 * 
	 * @Description This method interacts with BO class to fetch raw data
	 *              collections
	 * @return Array of {@link String} objects
	 */
	public String[] doFetchData() {
		Iterator<DBDataModel> it;
		DBDataModel model;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		int i = 0;
		List<DBDataModel> data = (List<DBDataModel>) dataservice.getAllData();
		String[] datafeed = new String[data.size() + 1];
		logger.info("datafeed length :" + datafeed.length);

		datafeed[i] = new String(
				"['Company Name', 'Functionality','Storage Type', 'Date', 'Usage'],");
		it = data.iterator();
		while (it.hasNext()) {
			model = it.next();
			i++;

			datafeed[i] = "['" + model.getCompanyName() + "','"
					+ model.getFunctionality() + "','" + model.getStoragetype()
					+ "',new Date('" + sdf.format(model.getDate()) + "'),"
					+ model.getUsageCount() + "],";

		}
		datafeed[i] = datafeed[i].substring(0, datafeed[i].length() - 1);
		return datafeed;
	}

	/**
	 * 
	 * @Description This method handles the request for exporting data data to
	 *              excel sheet. It uses the view {@link ExcelDataView} to
	 *              generate output
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/getCSV.do", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(
			@ModelAttribute("logDataList") List<LogDataModel> lt,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String output = ServletRequestUtils.getStringParameter(request,
				"output");

		if (output == null || "".equals(output)) {
			// return normal view
			return new ModelAndView("ExcelDataSummary", "rawData", lt);

		} else if ("CSV".equals(output.toUpperCase())) {
			// return excel view
			return new ModelAndView("ExcelDataSummary", "rawData", lt);

		} else {
			// return normal view
			return new ModelAndView("ExcelDataSummary", "rawData", lt);

		}
	}

}
