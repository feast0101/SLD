package com.kmbt.csa.dav.util;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.kmbt.csa.dav.pojo.LogDataModel;

public class CompanyMonthComparator implements Comparator {
	static Logger logger = Logger.getLogger("CompanyMonthComparator.class");

	public int compare(Object o1, Object o2) {
		logger.info("sorting on Month Name....");
		String compnm1 = ((LogDataModel) o1).getCompanyname();
		String compnm2 = ((LogDataModel) o2).getCompanyname();
		String month1 = ((LogDataModel) o1).getMonth();
		String month2 = ((LogDataModel) o2).getMonth();

		if (compnm1.equalsIgnoreCase(compnm2) && month1.equalsIgnoreCase(month2))
		   return 0;
		else if (compnm1.equalsIgnoreCase(compnm2) && !month1.equalsIgnoreCase(month2))
			return 1;
		else 
			return -1;
	}
}
