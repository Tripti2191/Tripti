package com.ctm.eai.purchaseorderservice;

import java.io.IOException;

import org.apache.velocity.VelocityContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.dataproviders.ServicesDataProvider;
import com.ctm.services.xml.ServicesHandler;
import com.ctm.services.CtmServiceLibraries;
import com.ctm.services.xml.XmlServiceLibraries;
import com.ctm.services.xml.ServiceAttributesContainer;
import com.ctm.services.xml.ServicePropertiesContainer;
import com.ctm.services.xml.XmlServiceVerificationLibraries;

import io.restassured.response.Response;
/**
 * Test to create purchase order through SOAP
 * 
 * @author praveen-bhasker
 *
 */
@Listeners({ com.ctm.report.CustomReport.class })

public class GetPurchaseOrderStatusTest extends BasePurchaseOrderTestLibrary implements PurchaseOrderService {
	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/CreatePurchaseOrder/GetPurchaseOrder.txt")
	public void testGetPurchaseOrder(String index, String sceanrioName, String consumerName,
			String consumerTransactionID,String lineStatus,String expectedResult) throws IOException {
		
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
						.getRequestBodyFromFile("ServiceData/CreatePurchaseOrder/GetPurchaseOrderStatusRequest.xml");
				String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,
						createContextForReplacement(consumerName, consumerTransactionID, lineStatus));
				propertiesContainer.setBodyOrEnvelope(templateReplacedBody);
				
				//Build service container and get response
				xmlServiceHandler.buildServiceContainer(propertiesContainer);
				ServiceAttributesContainer container = xmlServiceHandler.getServiceAttributesContainer();
				Response response = container.getResponse();
				response.prettyPrint();
				System.out.println(response.toString());
				xmlServiceVerificationLibraries.verifyStatusCode(response, 200);
				
				//Validation part
				if (expectedResult.equalsIgnoreCase("PASS")) {
					
					xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_GET_CONSUMER_NAME,
							consumerName);
					xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_GET_CONSUMER_TXN_ID ,
							consumerTransactionID);
					
					xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_GET_STATUS_DESCRIPTION,
						SUCCESS_MESSAGE);
					xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,NODE_GET_LINE_STATUS,lineStatus);
					
						}
				if (expectedResult.equalsIgnoreCase("FAIL")) {
					if (sceanrioName.equalsIgnoreCase("lineStatus")) {
						xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_ERROR_CODE,
								ERROR_CODE);
						xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_ERROR_REASON,
								REASON_CODE);
						xmlServiceVerificationLibraries.verifyStringFromResponseContainsValue(response, NODE_ERROR_MESSAGE,
								"string value '" + lineStatus + "' does not match pattern for OrderDateType in namespace ");
					}
					
					
				}		
								
}

	private VelocityContext createContextForReplacement(String consumerName, String consumerTransactionID,
			String lineStatus) {
		
		VelocityContext context = new VelocityContext();
		context.put("CONSUMER_NAME", consumerName);
		context.put("CONSUMER_TRANSACTION_ID", consumerTransactionID);
		context.put("LINE_STATUS",lineStatus);
		
		return context;
		
	}	
}
