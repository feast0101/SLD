package com.kmbt.csa.dav.util;

import java.util.Comparator;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kmbt.csa.dav.pojo.LogDataModel;

public class LogDateSorter implements Comparator<Object> {
	static Logger logger = Logger.getLogger("LogDateSorter.class");

	public int compare(Object o1, Object o2) {
	       
		logger.debug("sorting on date order basis....");
		Date date1 = ((LogDataModel) o1).getDate();
		Date date2 = ((LogDataModel) o2).getDate();
		return date1.compareTo(date2);
		
	}
}
