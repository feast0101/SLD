package com.kmbt.csa.dav.util;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.kmbt.csa.dav.pojo.LogDataModel;
import com.kmbt.csa.dav.pojo.WEEK;

public class WeekSorter implements Comparator<Object> {
	static Logger logger = Logger.getLogger("WeekSorter.class");

	public int compare(Object o1, Object o2) {
	/*	 final List<WEEK> correct =new ArrayList<WEEK>();
	        correct.add(WEEK.SUN);
	        correct.add(WEEK.MON);
	        correct.add(WEEK.TUE);
	        correct.add(WEEK.WED);
	        correct.add(WEEK.THR); 
	        correct.add(WEEK.FRI);
	        correct.add(WEEK.SAT); */
	       
		logger.debug("sorting on Weekly order basis....");
		WEEK wk1 = ((LogDataModel) o1).getWeekcode();
		WEEK wk2 = ((LogDataModel) o2).getWeekcode();
		//return Integer.valueOf(correct.indexOf(wk1)).compareTo(Integer.valueOf(correct.indexOf(wk2)));
		return Integer.valueOf(wk1.getWkcode()).compareTo(Integer.valueOf(wk2.getWkcode()));
        
	}
}
