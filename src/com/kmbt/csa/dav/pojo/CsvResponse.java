package com.kmbt.csa.dav.pojo;

import java.util.List;

public class CsvResponse {    
	   private final String filename;
	   private final List<LogDataModel> records;

	   public CsvResponse(List<LogDataModel> records, String filename) {
	       this.records = records;
	       this.filename = filename;
	   }
	   public String getFilename() {
	       return filename;
	   }
	   public List<LogDataModel> getRecords() {
	       return records;
	   }
	}