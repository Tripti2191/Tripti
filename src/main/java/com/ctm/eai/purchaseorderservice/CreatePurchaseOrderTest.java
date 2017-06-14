package com.ctm.eai.purchaseorderservice;

import org.testng.annotations.Test;

import com.ctm.common.utils.Utilities;
import com.ctm.core.CoreLibraries;
import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.annotation.ServiceRequestFile;
import com.ctm.services.dataproviders.ServicesDataProvider;
import com.ctm.services.xml.CommonServiceVerificationLibraries;
import com.ctm.services.xml.CtmServicesHandler;
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
	@ServiceRequestFile("ServiceData/CreatePurchaseOrder/CreatePurchaseOrderRequest.xml")
	public void testCreatePurchaseOrder() {
		//Instantiation Part 
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		CtmServicesHandler xmlServiceHandler = new CtmServicesHandler();
		CommonServiceVerificationLibraries lib = new CommonServiceVerificationLibraries();
		Utilities utils = new Utilities();

		//Set properties required to post the payload and get response
		propertiesContainer.setUserName(getData("testData.serviceUserName"));
		propertiesContainer.setPassword(getData("testData.servicePassword"));
		propertiesContainer.setIsSoap(true);
		String envelope = utils.readFile("/Users/praveen-bhasker/Desktop/ODP/EAI/PurchaseOrderService/envelope.xml");
		propertiesContainer.setBodyOrEnvelope(envelope);

		xmlServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = xmlServiceHandler.getServiceAttributesContainer();
		Response response = container.getResponse();
		lib.verifyStatusCode(response, 200);
		response.prettyPrint();
	}

}