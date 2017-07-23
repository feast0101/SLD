package com.kmbt.csa.dav.controller;

/*
 Copyright (c) 2015-2016 Konica Minolta
 Cloud Services Applications
 */
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kmbt.csa.dav.dao.impl.GraphDaoImpl;

/**
 * @author Kallol Das
 * @Description This controller servlet handles all the requests coming from
 *              user input page
 * @version 1.0
 * 
 */
@Controller
@SessionAttributes("logDataList")
public class UserGraphController {

	@Autowired
	GraphDaoImpl graphDao;

	private static final Logger logger = Logger
			.getLogger(UserGraphController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * @Description This method handles request for UserInput page. It also
	 *              prepares objects for page creation.
	 * @return {@link ModelAndView} object
	 * @throws IOException
	 */
	@RequestMapping(value = "/showUserInput.do", method = RequestMethod.GET)
	public ModelAndView showUserInput(Model model, HttpSession session)
			throws IOException {
		StringBuilder tables = new StringBuilder("[");
		String[] st = graphDao.getResourceData();
		int i = 0;
		for (String s : st) {
			tables.append("\"");
			tables.append(s);
			tables.append("\"");
			if (i != (st.length - 1))
				tables.append(",");
			i++;
		}

		tables.append("]");
		model.addAttribute("tables", tables.toString());
		return new ModelAndView("UserInput");
	}

	/**
	 * @Description This method handles the request for graph creation on the
	 *              basis of request parameters: graphType and tableName
	 * 
	 * @return {@link ModelAndView} object
	 * @throws IOException
	 */
	@RequestMapping(value = "/showUserGraph.do", method = RequestMethod.GET)
	public ModelAndView generateUserGraph(Model model,
			@RequestParam(value = "graphType") String graphType,
			@RequestParam(value = "tableName") String tableName,
			HttpSession session) throws IOException {

		String graphDataFeed;
		String errorMessage = "";
		try {
			graphDataFeed = prepareString(graphDao.getData(graphDao
					.getMetaData(tableName)));
		} catch (SQLException e) {
			graphDataFeed = "";
			errorMessage = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("graphtype", graphType);
		model.addAttribute("errorMessage", errorMessage);
		logger.info("graphtype :" + graphType + " tableName :" + tableName);
		model.addAttribute("graphdatafeed", graphDataFeed);
		return new ModelAndView("UserGraph");
	}

	/**
	 * @Description This method actually prepares string to be passed to Google
	 *              chart api
	 * @return {@link String} array of arrays
	 */
	private String prepareString(String[][] l) {
		StringBuilder sb = new StringBuilder("[");
		int i = 0;
		for (String[] st : l) {
			sb.append("[");
			int j = 0;
			for (String s : st) {
				if (s == null)
					continue;
				if (i < 1 || j < 1)
					sb.append("'");
				sb.append(s);
				if (i < 1 || j < 1)
					sb.append("'");
				if (j != (st.length - 1))
					sb.append(",");
				j++;
			}
			sb.append("]");
			if (i != (l.length - 1))
				sb.append(",");
			i++;
		}
		sb.append("]");
		String datafeed = sb.toString();
		logger.debug("datafeed :" + datafeed);
		return datafeed;
	}
}
