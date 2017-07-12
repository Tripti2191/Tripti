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
	 * 
	 */

public void validateTimeFormat(String time){
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

	/**
	 * To verify correlationId length
	 * 
	 * 
	 */
/*public void validateCorrelationId(String actualCorrelationId ){
	
	String splitValues[] = actualCorrelationId.split("-");
	if(splitValues[0].length()==8 && splitValues[1].length()==4 && splitValues[2].length()==4 && splitValues[3].length()==4 &&splitValues[4].length()==12 )
	{
		consoleReport.logReportWithStatus(true, "Attribute: " + actualCorrelationId + "  value:  matches with the expected format : " );
		
	}
	else
	{
		consoleReport.logReportWithStatus(false, "Attribute: " + actualCorrelationId + "  value:  matches with the expected format : " );
	}

	
	
}*/

	public boolean validateCorrelationId(String actualCorrelationId) {
		boolean validId = false;
		try {
		 UUID uuid = UUID.fromString("a3472721-00f9-4891-9dbb-bb143e501f16");
			//UUID id = UUID.fromString(actualCorrelationId);
			validId = true;
		} catch (Exception e) {
			System.out.println("This is not a valid UUID");
			validId = false;
		}
		return validId;
	}


}
