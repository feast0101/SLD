package com.kmbt.csa.dav.interceptors;

/*
 Copyright (c) 2014-2015 Konica Minolta
 Cloud Services Applications
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kmbt.csa.dav.bo.DataBo;
import com.kmbt.csa.dav.pojo.LogDataModel;

/**
 * @author Kallol Das
 * @Description This Interceptor class ensures that data is loaded from database
 *              and processed before handling any request from dashboard page
 * 
 * @version 1.0
 * 
 */
public class DataLoadingInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	DataBo dataservice;

	private static final Logger logger = Logger
			.getLogger(DataLoadingInterceptor.class);

	public List<LogDataModel> populateLogDataList() {
		return dataservice.populateLogData();
	}

	/**
	 * 
	 * @Description This method will be called before the actual handler will be
	 *              executed
	 * @return boolean
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		logger.info("Pre Processing Interceptor....");
	
		return true;
	}

	/**
	 * 
	 * @Description This method will be called after the actual handler is
	 *              executed
	 * @return boolean
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.info("Post Processing Interceptor....");
		HttpSession session = request.getSession();
		if (session.getAttribute("logDataList") == null) {
			logger.info("Populating logdataList.....");
			session.setAttribute("logDataList", populateLogDataList());

		}
	}
}