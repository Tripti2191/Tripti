package com.ctm.eai.purchaseorderservice;

import java.io.IOException;

import org.apache.velocity.VelocityContext;
import org.testng.annotations.Test;

import com.ctm.core.CoreLibraries;
import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.dataproviders.ServicesDataProvider;
import com.ctm.services.xml.CommonServiceVerificationLibraries;
import com.ctm.services.xml.CtmServicesHandler;
import com.ctm.services.xml.CtmXmlServiceLibraries;
import com.ctm.services.xml.ServiceAttributesContainer;
import com.ctm.services.xml.ServicePropertiesContainer;

import io.restassured.response.Response;

/**
 * Test to create purchase order through SOAP
 * 
 * @author praveen-bhasker
 *
 */
public class CreatePurchaseOrderTest extends CoreLibraries {

	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/CreatePurchaseOrder/CreatePurchaseOrder.txt")
	public void testCreatePurchaseOrder(String index, String consumerName, String consumerTransactionID,
			String requestID, String orderDate, String orderType, String vendorID, String totalLines,
			String totalQuantities, String expectedResult) throws IOException {

		//Instantiation Part 
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		CtmServicesHandler xmlServiceHandler = new CtmServicesHandler();
		CommonServiceVerificationLibraries serviceVerificationLibraries = new CommonServiceVerificationLibraries();
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

		//Validation part
		serviceVerificationLibraries.verifyStatusCode(response, 200);

		response.prettyPrint();
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