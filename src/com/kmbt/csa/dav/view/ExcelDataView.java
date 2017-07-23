package com.kmbt.csa.dav.view;
/*
Copyright (c) 2014-2015 Konica Minolta
Cloud Services Applications
*/
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.kmbt.csa.dav.pojo.LogDataModel;
/**
 * @author Kallol Das
 * @Description This view class populates data to excel sheet as response.This class is called by spring view resolver 
 * @version 1.0
 * 
 */
public class ExcelDataView extends AbstractJExcelView{

	@Override
	protected void buildExcelDocument(Map model, WritableWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<LogDataModel> lt  = (List<LogDataModel>) model.get("rawData");
		WritableSheet sheet = workbook.createSheet("Raw Data Report", 0);
		
        sheet.addCell(new Label(0, 0, "Company Name"));
        sheet.addCell(new Label(1, 0, ","));
        sheet.addCell(new Label(2, 0, "Service"));
        sheet.addCell(new Label(3, 0, ","));
        sheet.addCell(new Label(4, 0, "Date"));
        sheet.addCell(new Label(5, 0, ","));
        sheet.addCell(new Label(6, 0, "Usage Count"));
        sheet.addCell(new Label(7, 0, ","));
        sheet.addCell(new Label(8, 0, "Storage"));
        LogDataModel rawData = new LogDataModel();
        int rowNum = 1;
        Iterator<LogDataModel> it = lt.iterator();
        while(it.hasNext()){
        	rawData = it.next();
        
			//create the row data
			sheet.addCell(new Label(0, rowNum, rawData.getCompanyname()));
			sheet.addCell(new Label(1, rowNum, ","));
		    sheet.addCell(new Label(2, rowNum, rawData.getFunctionality()));
			sheet.addCell(new Label(3, rowNum, ","));
		    sheet.addCell(new Label(4, rowNum, rawData.getDate().toString()));
			sheet.addCell(new Label(5, rowNum, ","));
		    sheet.addCell(new Label(6, rowNum, String.valueOf(rawData.getUsagecount())));
			sheet.addCell(new Label(7, rowNum, ","));
		    sheet.addCell(new Label(8, rowNum, rawData.getStoragetype()));
		 
		    rowNum++;
        }
        
	}
}