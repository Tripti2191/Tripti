package com.ctm.eai.validateemployeeservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.ctm.core.CoreLibraries;
import com.ctm.logger.ConsoleReport;



/**
 * Base library for application or service specific methods
 * 
 * @author sindhu-kantamaneni*
 */

public class BaseValidateeEmployeeTestLibrary extends CoreLibraries {

	public ConsoleReport consoleReport = new ConsoleReport();

	/**
	 * To validate time format
	 * 
	 */

	public void validateTimeFormat(String time){
		boolean timeFormat = isValidFormat("yyyy-MM-dd HH:mm:ss.SSSSSS-HH:mm",time.replace("T", " " ));
		if(timeFormat)
			consoleReport.logReportWithStatusAndContinue(true, "Attribute: " + time + "  value  matches with the expected format" );
		else
			consoleReport.logReportWithStatusAndContinue(false, "Attribute: " + time + "  value does not  matches with the expected format" );
	}

	private boolean isValidFormat(String format, String value) {
		boolean isValidDateFormat = true;
		try {
			 SimpleDateFormat sdf = new SimpleDateFormat(format);
			 sdf.parse(value);
		} catch (ParseException pe) {
			 pe.printStackTrace();
			 isValidDateFormat = false;
		}
		return isValidDateFormat;
	}

	public void validateCorrelationId(String actualCorrelationId) {
		try {
			UUID.fromString(actualCorrelationId);
			consoleReport.logReportWithStatusAndContinue(true, "validateCorrelationId: " + actualCorrelationId + " matches with the expected format" );
		} catch (Exception e) {
			consoleReport.logReportWithStatusAndContinue(false, "validateCorrelationId: " + actualCorrelationId + " do not  match with the expected format" );
		}
	}


}
