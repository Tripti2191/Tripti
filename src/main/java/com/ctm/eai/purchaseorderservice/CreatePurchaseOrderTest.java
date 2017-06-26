package com.ctm.eai.purchaseorderservice;

import java.io.IOException;

import org.apache.velocity.VelocityContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.dataproviders.ServicesDataProvider;
import com.ctm.services.xml.CtmServicesHandler;
import com.ctm.services.xml.CtmXmlServiceLibraries;
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
public class CreatePurchaseOrderTest extends BasePurchaseOrderTestLibrary implements PurchaseOrderService {

	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/CreatePurchaseOrder/CreatePurchaseOrder.txt")
	public void testCreatePurchaseOrder(String index, String sceanrioName, String consumerName,
			String consumerTransactionID, String requestID, String orderDate, String orderType, String vendorID,
			String totalLines, String totalQuantities, String expectedResult) throws IOException {

		consoleReport.logTestMessage("Starting test scenario: " + sceanrioName);

		//Instantiation Part 
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		CtmServicesHandler xmlServiceHandler = new CtmServicesHandler();
		XmlServiceVerificationLibraries xmlServiceVerificationLibraries = new XmlServiceVerificationLibraries();
		CtmXmlServiceLibraries xmlServiceLibrary = new CtmXmlServiceLibraries();

		//Set properties required to post the payload and get response (setting 4 properties are mandatory. setUserName, setPassword, setIsSoap, setBodyOrEnvelope)
		propertiesContainer.setUserName(getData("testData.serviceUserName"));
		propertiesContainer.setPassword(getData("testData.servicePassword"));
		propertiesContainer.setIsSoap(true);
		String body = xmlServiceLibrary
				.getRequestBodyFromFile("ServiceData/CreatePurchaseOrder/CreatePurchaseOrderRequest.xml");
		String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,
				createContextForReplacement(consumerName, consumerTransactionID, requestID, orderDate, orderType,
						vendorID, totalLines, totalQuantities));
		propertiesContainer.setBodyOrEnvelope(templateReplacedBody);

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
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_REQUEST_ID, requestID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_STATUS_CODE,
					SUCCESS_STRING);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_STATUS_DESCRIPTION,
					SUCCESS_MESSAGE);
		}
		if (expectedResult.equalsIgnoreCase("FAIL")) {
			if (sceanrioName.equalsIgnoreCase("badDate")) {
				xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_ERROR_CODE,
						ERROR_CODE);
				xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_ERROR_REASON,
						REASON_CODE);
				xmlServiceVerificationLibraries.verifyStringFromResponseContainsValue(response, NODE_ERROR_MESSAGE,
						"string value '" + orderDate + "' does not match pattern for OrderDateType in namespace ");
			}
		}
	}

	private VelocityContext createContextForReplacement(String consumerName, String consumerTransactionID,
			String requestID, String orderDate, String orderType, String vendorID, String totalLines,
			String totalQuantities) {
		VelocityContext context = new VelocityContext();
		context.put("CONSUMER_NAME", consumerName);
		context.put("CONSUMER_TRANSACTION_ID", consumerTransactionID);
		context.put("REQUEST_ID", requestID);
		context.put("ORDER_DATE", orderDate);
		context.put("ORDER_TYPE", orderType);
		context.put("VENDOR_ID", vendorID);
		context.put("TOTAL_LINES", totalLines);
		context.put("TOTAL_QUANTITIES", totalQuantities);
		return context;
	}

}