package com.ctm.eai.taxExemptService;

import java.io.IOException;

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
 * @author amandeep-anand *
 */
@Listeners({ com.ctm.report.CustomReport.class })
public class ValidateTaxTest extends  BaseValidateTaxExemptionLibary implements validateTaxEmptionService {	
	
	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/taxExmeptService/ValidateTax.txt")
	public void testValidationTaxRequestABM(String index,String scenarioName,String consumerName, String consumerTransactionID,
			  String taxExemptID,  String stateOfRequest, String result) throws IOException {
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
		String body = xmlServiceLibrary.getRequestBodyFromFile("ServiceData/taxExmeptService/ValidateTaxRequest.xml");
		String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,createContextForReplacement(consumerName,consumerTransactionID ,  taxExemptID, stateOfRequest));
        propertiesContainer.setBodyOrEnvelope(templateReplacedBody);
		
		//Build service container and get response...
		xmlServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = xmlServiceHandler.getServiceAttributesContainer();
	    response = container.getResponse();
	    CommonServiceLibraries serviceLibrary = new CommonServiceLibraries();
	    response.prettyPrint();
		
		//Validation part.
		if (result.equalsIgnoreCase("PASS")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 200);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_NAME,consumerName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_TXN_ID,consumerTransactionID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_Tax_Exempt_ID,taxExemptID);
		}
		
		//failscenario.
		else if (scenarioName.equalsIgnoreCase("invalidScenario")||scenarioName.equalsIgnoreCase("negID")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 200);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_TXN_ID,consumerTransactionID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_Tax_Exempt_ID,taxExemptID);
			xmlServiceVerificationLibraries.verifyStringFromResponseContainsValue(response, NODE_CONSUMER_TXN_ID,"Transaction Id is Invalid: "+consumerTransactionID);
		}
		
		if (consumerName.contains("[a-zA-Z]+") == false && consumerName.length() > 2){
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_NAME,"Name cannot be of interger type"+consumerName);
		
		}
				
		else{
			xmlServiceVerificationLibraries.verifyStatusCode(response, 500);
		}
	}
	
	private VelocityContext createContextForReplacement(String consumerName, String consumerTransactionID,String taxExemptID, String stateOfRequest) {
		VelocityContext context = new VelocityContext();
		context.put("CONSUMER_NAME", consumerName);
		context.put("CONSUMER_TRANSACTION_ID", consumerTransactionID);
		context.put("NODE_Tax_Exempt_ID",taxExemptID);
		context.put("NODE_State_Of_Request",stateOfRequest);
		return context;
	}
}
	
