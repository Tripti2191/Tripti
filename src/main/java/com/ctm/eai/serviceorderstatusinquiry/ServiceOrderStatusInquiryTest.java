package com.ctm.eai.serviceorderstatusinquiry;

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
 * Test to create purchase order through SOAP
 * 
 * @author sindhu-kantamaneni
 *
 */
@Listeners({ com.ctm.report.CustomReport.class })
public class ServiceOrderStatusInquiryTest extends BaseServiceOrderStatusInquiryLibrary
		implements ServiceOrderStatusInquiryLibrary {

	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/ServiceOrderStatus/ServiceOrderStatus.txt")
	public void testServiceOrderStatus(String index, String sceanrioName, String requestNumber, String orderNumber,
			String replytoAddress, String faulttoAddress, String expectedResult) throws IOException {

		// Instantiation Part
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		ServicesHandler xmlServiceHandler = new ServicesHandler();
		XmlServiceVerificationLibraries xmlServiceVerificationLibraries = new XmlServiceVerificationLibraries();
		XmlServiceLibraries xmlServiceLibrary = new XmlServiceLibraries();

		// Set properties required to post the payload and get response (setting
		// 4 properties are mandatory. setUserName, setPassword, setIsSoap,
		// setBodyOrEnvelope)
		propertiesContainer.setUserName(getData("testData.serviceUserName"));
		propertiesContainer.setPassword(getData("testData.servicePassword"));
		propertiesContainer.setIsSoap(true);
		String body = xmlServiceLibrary.getRequestBodyFromFile("ServiceData/ServiceOrderStatus/ServiceOrderStatus.xml");
		String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,
				createContextForReplacement(requestNumber, orderNumber));
		propertiesContainer.setBodyOrEnvelope(templateReplacedBody);

		// Build service container and get response
		xmlServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = xmlServiceHandler.getServiceAttributesContainer();
		Response response = container.getResponse();
		response.prettyPrint();
		// response verification
		// method to validate the messaggeId
		if (expectedResult.equalsIgnoreCase("PASS")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 200);
			CommonServiceLibraries serviceLibrary = new CommonServiceLibraries();

			String actualMessageId = serviceLibrary.getAttributeValue(response, NODE_MESSAGEID);
			validateMessageID(actualMessageId);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_REPLYTO_ADDRESS,
					replytoAddress);
			String actualCompositeTime1 = serviceLibrary.getAttributeValue(response,
					NODE_REPLYTO_COMPOSITE_INSTANCE_CREATED_TIME);
			validateTime(actualCompositeTime1);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_FAULTTO_ADDRESS,
					faulttoAddress);
			String actualCompositeTime2 = serviceLibrary.getAttributeValue(response,
					NODE_FAULTTO_COMPOSITE_INSTANCE_CREATED_TIME);
			validateTime(actualCompositeTime2);
		}
	}

	private VelocityContext createContextForReplacement(String requestNumber, String orderNumber) {
		VelocityContext context = new VelocityContext();
		context.put("REQUEST_NUMBER", requestNumber);
		context.put("ORDER_NUMBER", orderNumber);
		return context;
	}

}
