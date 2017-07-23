package com.kmbt.csa.dav.util;

/*
 Copyright (c) 2013-2014 Konica Minolta
 Cloud Services Applications
 */
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.kmbt.csa.dav.pojo.LogDataModel;
import com.kmbt.csa.dav.pojo.WEEK;
/**
 * @author Kallol Das
 * @Description This utility class processes log data information and aggregates
 *               based on their monthly/weekly usage pattern
 * @version 1.0
 * 
 */
@Component
public class DataProcessor {

	private static Logger logger = Logger.getLogger(DataProcessor.class);
	/**
	* 
	* @Description This method aggregates total service usage on daily basis
	* @param {@link List} of {@link LogDataModel}
	* @return {@link Array} of {@link String} objects 
	*/
	public String[] doDayTotal(List<LogDataModel> list) {
		
		logger.info("......................Aggregating service usage count on daily basis........................");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		String[] datafeed = new String[list.size()+1];
		int i=0;
		Date tempdate =null;
		String tempmonthname = null;
		
		datafeed[i] = new  String("['Date','Follow Me', 'Smart Scan','Remote Print', 'Fax linkage', 'Remote Fax','GCP'],");
		
				int counter1 = 0;
				int counter2 = 0;
				int counter3 = 0;
				int counter4 = 0;
				int counter5 = 0;
				int counter6 = 0;
				int counter7 = 0;
				boolean flag =true;
				int temp = 0;
				LogDataModel ldm=new LogDataModel();
			
				LogDateSorter ms=new LogDateSorter();
			    Collections.sort(list,ms);
			    
			    
				Iterator<LogDataModel> it= list.iterator();
				
				while(it.hasNext()){
					
				ldm = it.next();
				flag =true;
				if(!ldm.getDate().equals(tempdate) && (tempdate!=null)){
					temp = 0;
					flag =false;
					i++;
					datafeed[i] = "[new Date('"+ sdf.format(tempdate) + "'),"
								+ counter1 + ","
								+ counter2 + ","
								+ counter3 + ","
								+ counter4 + ","
								+ counter5 + ","
								+ counter6 + "],";
			
							counter1=0;
							counter2=0;
							counter3=0;
							counter4=0;
							counter5=0;
							counter6=0;
						
				}
				if(ldm.getDate().equals(tempdate) || (tempdate==null) || !flag){
										
				switch(ldm.getFunctionality()){
				case "Follow-me":counter1+=ldm.getUsagecount();
				    break;
				case "Smart Scan":counter2+=ldm.getUsagecount();
					break;
				case "Remote Print":counter3+=ldm.getUsagecount();
					break;
				case "Fax linkage":counter4+=ldm.getUsagecount();
					break;
				case "Remote Fax":counter5+=ldm.getUsagecount();
				break;
				case "GCP":counter6+=ldm.getUsagecount();
				break;
				default:counter7+=ldm.getUsagecount();
				}
				temp ++;
			  }else if(flag){
				i++;
				datafeed[i] = "[new Date('"+ sdf.format(ldm.getDate()) + "'),"
							+ counter1 + ","
							+ counter2 + ","
							+ counter3 + ","
							+ counter4 + ","
							+ counter5 + ","
							+ counter6 + "],";
		
						counter1=0;
						counter2=0;
						counter3=0;
						counter4=0;
						counter5=0;
						counter6=0;
						flag= true;
				}
				tempmonthname = ldm.getMonth();
				tempdate = ldm.getDate();
				if (!it.hasNext()){
					i++;
					datafeed[i] = "[new Date('"+ sdf.format(ldm.getDate()) + "'),"
								+ counter1 + ","
								+ counter2 + ","
								+ counter3 + ","
								+ counter4 + ","
								+ counter5 + ","
								+ counter6 + "],";
			
							counter1=0;
							counter2=0;
							counter3=0;
							counter4=0;
							counter5=0;
							counter6=0;
					}	
				}
				datafeed[i] = datafeed[i].substring(0, datafeed[i].length() - 1);
				logger.debug("date service datafeed[]:"+datafeed);
				return datafeed;
	}
	/**
	* 
	* @Description This method aggregates total service usage as per monthly basis
	* @param {@link List} of {@link LogDataModel}
	* @return {@link Array} of {@link String} objects 
	*/
	public String[] doMonthlyTotal(List<LogDataModel> list) {
		
		logger.info("......................Aggregating service usage count on monthly basis........................");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		String[] datafeed = new String[list.size()+1];
		int i=0;
		Date tempdate =null;
		String tempmonthname = null;
		
		datafeed[i] = new  String("['Date', 'Month', 'Follow Me', 'Smart Scan','Remote Print', 'Fax linkage', 'Remote Fax','GCP'],");
		
				int counter1 = 0;
				int counter2 = 0;
				int counter3 = 0;
				int counter4 = 0;
				int counter5 = 0;
				int counter6 = 0;
				int counter7 = 0;
				boolean flag =true;
				int temp = 0;
				LogDataModel ldm=new LogDataModel();
			
				LogMonthSorter ms=new LogMonthSorter();
			    Collections.sort(list,ms);
			    
				Iterator<LogDataModel> it= list.iterator();
				
				while(it.hasNext()){
					
				ldm = it.next();
				flag =true;
				if(!ldm.getMonth().equalsIgnoreCase(tempmonthname) && (tempmonthname!=null)){
					temp = 0;
					flag =false;
					i++;
					datafeed[i] = "[new Date('"+ sdf.format(tempdate) + "'),'"
								+ tempmonthname + "',"
								+ counter1 + ","
								+ counter2 + ","
								+ counter3 + ","
								+ counter4 + ","
								+ counter5 + ","
								+ counter6 + "],";
			
							counter1=0;
							counter2=0;
							counter3=0;
							counter4=0;
							counter5=0;
							counter6=0;
						
				}
				if(ldm.getMonth().equalsIgnoreCase(tempmonthname) || (tempmonthname==null) || !flag){
										
				switch(ldm.getFunctionality()){
				case "Follow-me":counter1+=ldm.getUsagecount();
				    break;
				case "Smart Scan":counter2+=ldm.getUsagecount();
					break;
				case "Remote Print":counter3+=ldm.getUsagecount();
					break;
				case "Fax linkage":counter4+=ldm.getUsagecount();
					break;
				case "Remote Fax":counter5+=ldm.getUsagecount();
				break;
				case "GCP":counter6+=ldm.getUsagecount();
				break;
				default:counter7+=ldm.getUsagecount();
				}
				temp ++;
			  }else if(flag){
				i++;
				datafeed[i] = "[new Date('"+ sdf.format(ldm.getDate()) + "'),'"
							+ ldm.getMonth() + "',"
							+ counter1 + ","
							+ counter2 + ","
							+ counter3 + ","
							+ counter4 + ","
							+ counter5 + ","
							+ counter6 + "],";
		
						counter1=0;
						counter2=0;
						counter3=0;
						counter4=0;
						counter5=0;
						counter6=0;
						flag= true;
				}
				tempmonthname = ldm.getMonth();
				tempdate = ldm.getDate();
				if (!it.hasNext()){
					i++;
					datafeed[i] = "[new Date('"+ sdf.format(ldm.getDate()) + "'),'"
								+ ldm.getMonth() + "',"
								+ counter1 + ","
								+ counter2 + ","
								+ counter3 + ","
								+ counter4 + ","
								+ counter5 + ","
								+ counter6 + "],";
			
							counter1=0;
							counter2=0;
							counter3=0;
							counter4=0;
							counter5=0;
							counter6=0;
					}	
				}
				datafeed[i] = datafeed[i].substring(0, datafeed[i].length() - 1);
				logger.debug("monthly service datafeed[]:"+datafeed);
				return datafeed;
	}
	/**
	* 
	* @Description This method aggregates total service usage as per weekly basis
	* @param {@link List} of {@link LogDataModel}
	* @return {@link Array} of {@link String} objects 
	*/
	public String[] doWeeklyTotal(List<LogDataModel> list) {
		logger.info("......................Aggregating service usage count on Weekly basis........................");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		String[] datafeed = new String[list.size()+1];
		int i=0;
		String tempmonthname = null;
		WEEK tempweekname = null;
		Date tempdate =null;
		datafeed[i] = new  String("['Date', 'Month','Week', 'Follow Me', 'Smart Scan','Remote Print', 'Fax linkage', 'Remote Fax','GCP'],");
		
				int counter1 = 0;
				int counter2 = 0;
				int counter3 = 0;
				int counter4 = 0;
				int counter5 = 0;
				int counter6 = 0;
				int counter7 = 0;
				boolean flag =true;
				int temp = 0;
				LogDataModel ldm=new LogDataModel();
			
				LogMonthSorter ms=new LogMonthSorter();
			    Collections.sort(list,ms);
			    WeekSorter ws=new WeekSorter();
			    Collections.sort(list,ws);
			    
				Iterator<LogDataModel> it= list.iterator();
				
				while(it.hasNext()){
				ldm = it.next();
				flag =true;
				if(!((ldm.getMonth().equalsIgnoreCase(tempmonthname) || (tempmonthname==null)) && (ldm.getWeekcode().equals(tempweekname) || (tempweekname==null)))){
					temp = 0;
					flag =false;
					i++;
					datafeed[i] = "[new Date('"+ sdf.format(ldm.getDate()) + "'),'"
								+ tempmonthname + "','"
								+ tempweekname + "',"
								+ counter1 + ","
								+ counter2 + ","
								+ counter3 + ","
								+ counter4 + ","
								+ counter5 + ","
								+ counter6 + "],";
			
							counter1=0;
							counter2=0;
							counter3=0;
							counter4=0;
							counter5=0;
							counter6=0;
						
				}
				if(((ldm.getMonth().equalsIgnoreCase(tempmonthname) || (tempmonthname==null)) && (ldm.getWeekcode().equals(tempweekname) || (tempweekname==null))) || !flag){
						
				switch(ldm.getFunctionality()){
				case "Follow-me":counter1+=ldm.getUsagecount();
				    break;
				case "Smart Scan":counter2+=ldm.getUsagecount();
					break;
				case "Remote Print":counter3+=ldm.getUsagecount();
					break;
				case "Fax linkage":counter4+=ldm.getUsagecount();
					break;
				case "Remote Fax":counter5+=ldm.getUsagecount();
				break;
				case "GCP":counter6+=ldm.getUsagecount();
				break;
				default:counter7+=ldm.getUsagecount();
				}
				temp ++;
			   }else if(flag){
				i++;
				datafeed[i] = "[new Date('"+ sdf.format(ldm.getDate()) + "'),'"
							+ ldm.getMonth() + "','"
							+ ldm.getWeekcode() + "',"
							+ counter1 + ","
							+ counter2 + ","
							+ counter3 + ","
							+ counter4 + ","
							+ counter5 + ","
							+ counter6 + "],";
		
						counter1=0;
						counter2=0;
						counter3=0;
						counter4=0;
						counter5=0;
						counter6=0;
				}
				tempmonthname = ldm.getMonth();
				tempweekname = ldm.getWeekcode();
				tempdate = ldm.getDate();
				if (!it.hasNext()){
					i++;
					datafeed[i] = "[new Date('"+ sdf.format(ldm.getDate()) + "'),'"
								+ ldm.getMonth() + "','"
								+ ldm.getWeekcode() + "',"
								+ counter1 + ","
								+ counter2 + ","
								+ counter3 + ","
								+ counter4 + ","
								+ counter5 + ","
								+ counter6 + "],";
			
							counter1=0;
							counter2=0;
							counter3=0;
							counter4=0;
							counter5=0;
							counter6=0;
					}	
				}
				datafeed[i] = datafeed[i].substring(0, datafeed[i].length() - 1);
				for(String t: datafeed)
				logger.debug("weekly service datafeed[]:"+t);
				return datafeed;
	}
	/**
	* 
	* @Description This method aggregates total storage usage as per monthly basis
	* @param {@link List} of {@link LogDataModel}
	* @return {@link Array} of {@link String} objects 
	*/
	public String[] doMonthlyStorageTotal(List<LogDataModel> list) {
logger.info("......................Aggregating Storage usage data on monthly basis........................");


String[] datafeed = new String[list.size()+1];
int i=0;
datafeed[i] = new  String("['Month', 'Box.com', 'Drop Box', 'Google Drive','One Drive'],");
		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;
		int counter4 = 0;
		int counter5 = 0;
		boolean flag =true;
		int temp = 0;
		String tempmonth=null;
		String stype=null;
		LogDataModel ldm=new LogDataModel();
	
		LogMonthSorter ms=new LogMonthSorter();
	    Collections.sort(list,ms);
	    
		Iterator<LogDataModel> it= list.iterator();
		
		while(it.hasNext()){
			ldm = it.next();
			flag =true;
			if(!ldm.getMonth().equalsIgnoreCase(tempmonth) && (tempmonth!=null)){
				temp = 0;
				flag =false;
				i++;
				datafeed[i] = "['" + tempmonth + "',"
						+ counter1 + ","
						+ counter2 + ","
						+ counter3 + ","
						+ counter4 + "],";

					counter1=0;
					counter2=0;
					counter3=0;
					counter4=0;
					
			}
			if(ldm.getMonth().equalsIgnoreCase(tempmonth) || (tempmonth==null) || !flag){
			stype=(ldm.getStoragetype()== null)?"NULL":ldm.getStoragetype();
			
		switch(stype){
		case "Box.com":counter1+=ldm.getUsagecount();
		    break;
		case "DropBox":counter2+=ldm.getUsagecount();
			break;
		case "Google Drive":counter3+=ldm.getUsagecount();
			break;
		case "One Drive":counter4+=ldm.getUsagecount();
			break;
		default:counter5+=ldm.getUsagecount();
		}
		temp ++;
	   }else if(flag){ 
		    i++;
			datafeed[i] = "['" + tempmonth + "',"
					+ counter1 + ","
					+ counter2 + ","
					+ counter3 + ","
					+ counter4 + "],";

				counter1=0;
				counter2=0;
				counter3=0;
				counter4=0;
				flag= true;
			}
			
		tempmonth=ldm.getMonth();
			if (!it.hasNext()){
			i++;
			datafeed[i] = "['" + tempmonth + "',"
					+ counter1 + ","
					+ counter2 + ","
					+ counter3 + ","
					+ counter4 + "],";

				counter1=0;
				counter2=0;
				counter3=0;
				counter4=0;
			}	
		}
		datafeed[i] = datafeed[i].substring(0, datafeed[i].length() - 1);
		logger.debug("storage datafeed[] :"+datafeed);
		return datafeed;
  }
}