package com.ctm.eai.validateemployeeservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.velocity.VelocityContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ctm.services.*;

import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.dataproviders.ServicesDataProvider;
import com.ctm.services.xml.ServicesHandler;
import com.ctm.services.xml.XmlServiceLibraries;
import com.ctm.services.xml.CommonServiceLibraries;
import com.ctm.services.xml.ServiceAttributesContainer;
import com.ctm.services.xml.ServicePropertiesContainer;
import com.ctm.services.xml.XmlServiceVerificationLibraries;

import io.restassured.response.Response;

/**
 * Test to ValidateEmployeeTest  through SOAP
 * 
 * @author sindhu-kantamaneni
 *
 */
@Listeners({ com.ctm.report.CustomReport.class })
public class ValidateEmployeeTest extends  BaseValidateeEmployeeTestLibrary implements ValidateEmployeeService {	
	Response response = null;
	
	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/ValidateEmployeeService/ValidateEmployee.txt")
	public void testValidateEmployeeRequestABM(String index,String sceanrioName,String consumerName, String consumerTransactionID,String employeeID,String serviceName, String serviceOperation, String sensitiveData, String result, 
			String firstName, String lastName ,String receivedTime,String correlationId, String expectedResult) throws IOException {
    consoleReport.logTestMessage("Starting test scenario: " + sceanrioName);

		//Instantiation Part 
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		ServicesHandler xmlServiceHandler = new ServicesHandler();
		XmlServiceVerificationLibraries xmlServiceVerificationLibraries = new XmlServiceVerificationLibraries();
		XmlServiceLibraries xmlServiceLibrary = new XmlServiceLibraries();

		//Set properties required to post the payload and get response (setting 4 properties are mandatory. setUserName, setPassword, setIsSoap, setBodyOrEnvelope)
		propertiesContainer.setUserName(getData("testData.serviceUserName"));
		propertiesContainer.setPassword(getData("testData.servicePassword"));
		propertiesContainer.setIsSoap(true);
		String body = xmlServiceLibrary.getRequestBodyFromFile("ServiceData/ValidateEmployeeService/ValidateEmployeeRequest.xml");
		String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,createContextForReplacement(consumerName,consumerTransactionID , employeeID));
        propertiesContainer.setBodyOrEnvelope(templateReplacedBody);
		
		//Build service container and get response 
		xmlServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = xmlServiceHandler.getServiceAttributesContainer();
	    response = container.getResponse();
	    CommonServiceLibraries serviceLibrary = new CommonServiceLibraries();
	    response.prettyPrint();
		
		
		//Validation part
		if (expectedResult.equalsIgnoreCase("PASS")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 200);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_NAME,
				consumerName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_TXN_ID,
					consumerTransactionID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_SERVICE_NAME,serviceName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_SERVICE_OPERATION,serviceOperation);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_CONTAINS_SENSITIVE_DATA,sensitiveData);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_RESULT,result);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_FIRST_NAME,firstName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_LAST_NAME,lastName);
			String time = serviceLibrary.getAttributeValue(response, NODE_TIME_RECEIVED);
			validateTime(time);
			String actualCorrelationId = serviceLibrary.getAttributeValue(response, NODE_CORELATION_ID);
		    xmlServiceVerificationLibraries.verifyIntegerEquals(new Integer(actualCorrelationId.length()),CORRELATIONID_LENGTH);
			}
		
		//failscenario
		if (expectedResult.equalsIgnoreCase("FAIL")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 500);
			if (sceanrioName.equalsIgnoreCase("invalidId")) {
				xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_FAULT_STRING,REASON_CODE);
				xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_ERROR_CODE,ERROR_CODE);
				xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_ERROR_MESSAGE,ERROR_MESSAGE);								
				
			} 
		}
		
	}
	
	private VelocityContext createContextForReplacement(String consumerName, String consumerTransactionID,
			String employeeID) {
		VelocityContext context = new VelocityContext();
		context.put("CONSUMER_NAME", consumerName);
		context.put("CONSUMER_TRANSACTION_ID", consumerTransactionID);
		context.put("EMPLOYEE_ID",employeeID);
		return context;
	}
}
	
