package com.ctm.eai.serviceorderstatusinquiry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ctm.core.CoreLibraries;
import com.ctm.logger.ConsoleReport;



/**
 * Base library for application or service specific methods
 * 
 * @author praveen-bhasker*
 */
public class BaseServiceOrderStatusInquiryLibrary extends CoreLibraries {

	public ConsoleReport consoleReport = new ConsoleReport();


	public void validateTime(String time){
		boolean timeFormat = isValidFormat("yyyy-MM-dd HH:mm:ss.SSSSSS-HH:mm",time.replace("T", " " ));
		if(timeFormat)

			consoleReport.logReportWithStatus(true, "Attribute: " + time + "  value  matches with the expected format" );
		else
			consoleReport.logReportWithStatus(false, "Attribute: " + time + "  value does not  matches with the expected format" );
	}

	private boolean isValidFormat(String format, String value) {
			Date date = null;
			boolean isValidDateFormat = true;
			try {
				 SimpleDateFormat sdf = new SimpleDateFormat(format);
				 date = sdf.parse(value);
			} catch (ParseException pe) {
				 pe.printStackTrace();
				 isValidDateFormat = false;
			}
			return isValidDateFormat;
		}


	public void validateMessageID(String actualMessageId){
		String messageId[]=null;
		if (actualMessageId!= null && actualMessageId.contains(":")){
			  messageId=actualMessageId.split(":");		
		}
		String messageIdPart1=messageId[0];
		String messageIdPart2=messageId[1];
		if (messageIdPart1=="urn" && messageIdPart2.length()==32){
			System.out.println("message is valid");
			
		}
		
	}
	
}









