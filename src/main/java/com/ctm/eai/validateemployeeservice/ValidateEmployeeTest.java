package com.ctm.eai.validateemployeeservice;

import java.io.IOException;
import org.apache.velocity.VelocityContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ctm.services.*;

import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.dataproviders.ServicesDataProvider;
import com.ctm.services.xml.ServicesHandler;
import com.ctm.services.xml.XmlServiceLibraries;
import com.ctm.services.xml.ServiceAttributesContainer;
import com.ctm.services.xml.ServicePropertiesContainer;
import com.ctm.services.xml.XmlServiceVerificationLibraries;

import io.restassured.response.Response;

/**
 * Test to create purchase order through SOAP
 * 
 * @author sindhu-kantamaneni
 *
 */
@Listeners({ com.ctm.report.CustomReport.class })
public class ValidateEmployeeTest extends  BaseValidateeEmployeeTestLibrary implements ValidateEmployeeService {
	
	
	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/ValidateEmploeeService/validateEmployee.txt")
	public void testValidateEmployeeRequestABM(String index,String sceanrioName,String consumerName, String consumerTransactionID,String employeeID,String serviceName, String serviceOperation,String sensitiveData, String result,String firstName,String lastName, String expectedResult) throws IOException {


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
		String body = xmlServiceLibrary
				.getRequestBodyFromFile("ServiceData/ValidateEmployeeService/ValidateEmployeeRequest.xml");
		System.out.println("before templAte replaced body");
		String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,
				createContextForReplacement(consumerName, employeeID,consumerTransactionID ));
		System.out.println("set body");
		propertiesContainer.setBodyOrEnvelope(templateReplacedBody);
		System.out.println("After set  body");
		//Build service container and get response
		xmlServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = xmlServiceHandler.getServiceAttributesContainer();
		Response response = container.getResponse();
		
		xmlServiceVerificationLibraries.verifyStatusCode(response, 200);
		
		
		//Validation part
		if (expectedResult.equalsIgnoreCase("PASS")) {
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_NAME,
					consumerName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_TXN_ID,
					consumerTransactionID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_SERVICE_NAME,serviceName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_SERVICE_OPERATION,serviceOperation);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,   NODE_CONTAINS_SENSITIVE_DATA,sensitiveData);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,   NODE_RESULT,result);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,   NODE_FIRST_NAME,firstName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,  NODE_LAST_NAME,lastName);
			}
		/*if (expectedResult.equalsIgnoreCase("FAIL")) {
			if (sceanrioName.equalsIgnoreCase("badDate")) {
				xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_ERROR_CODE,
						ERROR_CODE);
				xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_ERROR_REASON,
						REASON_CODE);
				
				xmlServiceVerificationLibraries.verifyStringFromResponseContainsValue(response, NODE_ERROR_MESSAGE,
						"string value '" + employeeID  + "' does not match pattern for OrderDateType in namespace ");
				
			} 
		}*/
	}
	
	private VelocityContext createContextForReplacement(String consumerName, String consumerTransactionID,
			String employeeID) {
		VelocityContext context = new VelocityContext();
		context.put("CONSUMER_NAME", consumerName);
		context.put("CONSUMER_TRANSACTION_ID", consumerTransactionID);
		//context.put("CONSUMER_EMPLOYEE_ID",serviceName);
		//context.put("CONSUMER_EMPLOYEE_ID",serviceOperation);
		//context.put("CONSUMER_EMPLOYEE_ID",sensitiveData);
		context.put("CONSUMER_EMPLOYEE_ID",employeeID);
		
		return context;
	}
	
	public CtmServiceLibraries getCtmServiceLibraries(){

		CtmServiceLibraries ctmServiceLibraries = new CtmServiceLibraries();
		ctmServiceLibraries.getCountOfAllAttributes("sindhu");
		return ctmServiceLibraries;
	}
}
	
