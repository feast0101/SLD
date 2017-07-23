package com.kmbt.csa.dav.util;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.kmbt.csa.dav.pojo.LogDataModel;
import com.kmbt.csa.dav.pojo.WEEK;

public class CompanyMonthWeekComparator implements Comparator {
	static Logger logger = Logger.getLogger("CompanyMonthWeekComparator.class");

	public int compare(Object o1, Object o2) {
		logger.info("sorting on Week code....");
		String compnm1 = ((LogDataModel) o1).getCompanyname();
		String compnm2 = ((LogDataModel) o2).getCompanyname();
		String month1 = ((LogDataModel) o1).getMonth();
		String month2 = ((LogDataModel) o2).getMonth();
		
		int week1 = ((LogDataModel) o1).getWeekcode()== null?-1:((LogDataModel) o1).getWeekcode().getWkcode();
		int week2 = ((LogDataModel) o2).getWeekcode()== null?-1:((LogDataModel) o2).getWeekcode().getWkcode();

		if ((week1==week2) && compnm1.equalsIgnoreCase(compnm2) && month1.equalsIgnoreCase(month2))
		   return 0;
		else if (compnm1.equalsIgnoreCase(compnm2) && month1.equalsIgnoreCase(month2) && (week1!=week2))
			return 1;
		else 
			return -1;
	}
}
