package com.ctm.eai.validateemployeeservice;

import java.io.IOException;
import java.util.HashMap;

import org.apache.velocity.VelocityContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.common.CommonServiceLibraries;
import com.ctm.services.common.ServiceAttributesContainer;
import com.ctm.services.common.ServicePropertiesContainer;
import com.ctm.services.common.ServicesHandler;
import com.ctm.services.dataproviders.ServicesDataProvider;
import com.ctm.services.xml.XmlServiceLibraries;
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
	
	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/ValidateEmployeeService/ValidateEmployee.txt")
	public void testValidateEmployeeRequestABM(String index,String scenarioName,String consumerName, String consumerTransactionID,String employeeID,String serviceName, String serviceOperation, String sensitiveData, String result, 
			String firstName, String lastName , String expectedResult) throws IOException {
    consoleReport.logTestMessage("Starting test scenario: " + scenarioName);

		
       //Instantiation Part 
	    Response response = null;
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		ServicesHandler xmlServiceHandler = new ServicesHandler();
		XmlServiceVerificationLibraries xmlServiceVerificationLibraries = new XmlServiceVerificationLibraries();
		XmlServiceLibraries xmlServiceLibrary = new XmlServiceLibraries();	
	

		//Set properties required to post the payload and get response (setting 4 properties are mandatory. setUserName, setPassword, setIsSoap, setBodyOrEnvelope)
		propertiesContainer.setUserName(getData("testData.serviceUserName"));
		propertiesContainer.setPassword(getData("testData.servicePassword"));
		propertiesContainer.setIsSoap(true);
		String body = xmlServiceLibrary.getRequestBodyFromFile("ServiceData/ValidateEmployeeService/ValidateEmployeeRequest.xml");
		HashMap<String,String> nameValue=new HashMap<String,String>();
		nameValue.put("CONSUMER_NAME", consumerName); nameValue.put("CONSUMER_TRANSACTION_ID", consumerTransactionID); nameValue.put("EMPLOYEE_ID",employeeID);
		String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,xmlServiceLibrary.getContextObject(nameValue));
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
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_NAME,consumerName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_TXN_ID,consumerTransactionID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_SERVICE_NAME,serviceName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_SERVICE_OPERATION,serviceOperation);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_CONTAINS_SENSITIVE_DATA,sensitiveData);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_RESULT,result);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_FIRST_NAME,firstName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_LAST_NAME,lastName);
			String time = serviceLibrary.getAttributeValue(response, NODE_TIME_RECEIVED);
			validateTimeFormat(time);
			String actualCorrelationId = serviceLibrary.getAttributeValue(response, NODE_CORELATION_ID);
			validateCorrelationId(actualCorrelationId);
			}
		
		//fail scenario
		else if (scenarioName.equalsIgnoreCase("invalidId")||scenarioName.equalsIgnoreCase("negID")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 500);
			xmlServiceVerificationLibraries.verifyStringFromResponseContainsValue(response, NODE_FAULT_STRING,"Employee ID not found at PeopleSoft  for Employee ID: "+employeeID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_ERROR_CODE,ERROR_CODE);
			xmlServiceVerificationLibraries.verifyStringFromResponseContainsValue(response,  NODE_ERROR_MESSAGE,"Employee ID not found at PeopleSoft  for Employee ID: "+employeeID);
		}
		
		else if (scenarioName.equalsIgnoreCase("empIDEmpty")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 500);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_NULLEMPID_FAULT_STRING,EMPIDNULL_REASON_CODE);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_NULLEMPID_ERROR_CODE,EMPIDNULL_ERROR_CODE);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_NULLEMPID_REASON,EMPIDNULL_ERROR_MESSAGE);
		}
				
		else{
			xmlServiceVerificationLibraries.verifyStatusCode(response, 500);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_BIGID_FAULT_STRING,"Input has failed validation, please check the input  for Employee ID: "+employeeID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_BIGID_ERROR_CODE,BIGID_ERROR_CODE);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_BIGID_ERROR_MESSAGE,"Input has failed validation, please check the input  for Employee ID: "+employeeID);
		}
	}
}
	
