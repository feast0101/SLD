package com.kmbt.csa.dav.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.kmbt.csa.dav.pojo.CsvResponse;
import com.kmbt.csa.dav.pojo.LogDataModel;

public class CsvMessageConverter extends AbstractHttpMessageConverter<CsvResponse> {
	   public static final MediaType MEDIA_TYPE = new MediaType("application", "csv", Charset.forName("UTF-8"));
	   public CsvMessageConverter() {
	       super(MEDIA_TYPE);
	   }

	   protected boolean supports(Class<?> clazz) {
	       return CsvResponse.class.equals(clazz);
	   }

	   protected void writeInternal(CsvResponse response, HttpOutputMessage output) throws IOException, HttpMessageNotWritableException {
	       output.getHeaders().setContentType(MEDIA_TYPE);
	       output.getHeaders().set("Content-Disposition", "attachment; filename=\"" + response.getFilename() + "\"");
/*	       OutputStream out = output.getBody();
	       CsvWriter writer = new CsvWriter(new OutputStreamWriter(out), '\u0009');
	       LogDataModel aReq =new LogDataModel();
	       List<LogDataModel> allRecords = response.getRecords();
	       Iterator<LogDataModel> it=allRecords.iterator();
	       while(it.hasNext()){
	    	   aReq=it.next();
	    	   writer.write("2Helllllllllllo!!");
	       }*/
	       /*for (int i = 1; i < allRecords.size(); i++) {
	    	   LogDataModel aReq = allRecords.get(i);
	    	   
	    	   writer.write(aReq.toString());
	       }*/
	       output.getBody().write("Helllllllllllo!!".getBytes());
	      // writer.close();
	   }

	@Override
	protected CsvResponse readInternal(Class<? extends CsvResponse> arg0,
			HttpInputMessage arg1) throws IOException, HttpMessageNotReadableException {

    	String st = convertStreamToString(arg1.getBody());
    	System.out.println(">>>>>"+st);
        
	   	CsvReader reader=new CsvReader("Test");
    	List<LogDataModel> records=new ArrayList();
    	CsvResponse response =new CsvResponse(records,"MyData.csv");
    	return response;
	}
	 //TODO: move this to a more appropriated utils class
    public String convertStreamToString(InputStream is) throws IOException {
        /*
         * To convert the InputStream to String we use the Reader.read(char[]
         * buffer) method. We iterate until the Reader return -1 which means
         * there's no more data to read. We use the StringWriter class to
         * produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            
            return writer.toString();
        } else {
            return "";
        }
    }
	
	}