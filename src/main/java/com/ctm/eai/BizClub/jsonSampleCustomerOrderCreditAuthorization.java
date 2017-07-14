package com.ctm.eai.BizClub;

import org.apache.velocity.VelocityContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ctm.services.annotation.ServiceDataFile;
import com.ctm.services.common.CommonServiceLibraries;
import com.ctm.services.common.ServiceAttributesContainer;
import com.ctm.services.common.ServicePropertiesContainer;
import com.ctm.services.common.ServicesHandler;
import com.ctm.services.dataproviders.ServicesDataProvider;

import io.restassured.response.Response;

/**
 * Test the Bizbox service, endpoint CustomerOrderCreditAuthorization
 * @author tejasvee-saini
 *
 */
@Listeners({ com.ctm.report.CustomReport.class })
public class jsonSampleCustomerOrderCreditAuthorization extends BizClub_Libs{
	
	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/BizBox/CustomerOrderCreditAuthorization.txt")
	public void testCustomerOrderCreditAuthorization(String index, String schoolID){
		//Instantiation Part 
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		ServicesHandler objServiceHandler = new ServicesHandler();
		CommonServiceLibraries objCommonServiceLibraries = new CommonServiceLibraries();
		
		//Set properties required to post the payload and get response
		propertiesContainer.setIsRest(true);
		propertiesContainer.setIsGetOrPost("Post");
		String body = objCommonServiceLibraries.getRequestBodyFromFile("ServiceData/BizBox/CustomerOrderCreditAuthorization.json");
		String templateReplacedBody = objCommonServiceLibraries.replaceTemplateWithValues(body,createContextForReplacement(schoolID));
		propertiesContainer.setBodyOrEnvelope(templateReplacedBody);
		
		//Build service container and get response
		objServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = objServiceHandler.getServiceAttributesContainer();
		Response response = container.getResponse();
		response.prettyPrint();
		
		
	}
	
	private VelocityContext createContextForReplacement(String schoolID) {
		VelocityContext context = new VelocityContext();
		context.put("schoolID", schoolID);
		return context;
	}
}
