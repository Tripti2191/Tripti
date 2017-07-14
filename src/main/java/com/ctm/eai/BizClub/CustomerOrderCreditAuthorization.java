package com.ctm.eai.BizClub;

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
public class CustomerOrderCreditAuthorization extends BizClub_Libs{
	
	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/BizClub/CustomerOrderCreditAuthorization.txt")
	public void testCustomerOrderCreditAuthorization(String index,String expectedResult,String consumerName,String consumerTransactionId,String consumerTransactionDateTime,String firstName,String middleName,String lastName,String cardHighValueToken,String expirationDate,String cardType,String amount,String applicationTransactionNumber,String name,String bill_address1,String bill_address2,String bill_city,String bill_state,String bill_postalCode,String bill_country,String orderType,String brandType,String orderSource,String customerOrderNumber,String originalOrderNumber,String orderDate,String acc_costCenter,String acc_costCenterDesc,String poNumber,String acc_release,String serialNumber,String subTotal,String totals_tax,String delivery,String totals_discount,String misc,String total,String lineNumber,String sku,String odSku,String customerSku,String vendorProductCode,String customProductCode,String quantity,String unitPrice,String item_tax,String item_discount,String cost,String unitOfMeasure,String description,String comments,String item_costCenter,String item_costCenterDesc,String costCenterSeq,String costCenterRoute,String item_release,String configID,String bundleID,String invLoc,String InventoryLocation,String id,String seq,String Name,String ship_address1,String ship_address2,String address3,String ship_city,String ship_state,String ship_postalCode,String ship_country,String email,String phoneNumber,String faxNumber){
		//Instantiation Part 
		ServicePropertiesContainer propertiesContainer = new ServicePropertiesContainer();
		ServicesHandler objServiceHandler = new ServicesHandler();
		CommonServiceLibraries objCommonServiceLibraries = new CommonServiceLibraries();
		
		//Set properties required to post the payload and get response
		propertiesContainer.setIsRest(true);
		propertiesContainer.setIsGetOrPost("Post");
		String body = objCommonServiceLibraries.getRequestBodyFromFile("ServiceData/BizClub/CustomerOrderCreditAuthorization.json");
		String[] attrNames={"consumerName","consumerTransactionId","consumerTransactionDateTime","firstName","middleName","lastName","cardHighValueToken","expirationDate","cardType","amount","applicationTransactionNumber","name","bill_address1","bill_address2","bill_city","bill_state","bill_postalCode","bill_country","orderType","brandType","orderSource","customerOrderNumber","originalOrderNumber","orderDate","acc_costCenter","acc_costCenterDesc","poNumber","acc_release","serialNumber","subTotal","totals_tax","delivery","totals_discount","misc","total","lineNumber","sku","odSku","customerSku","vendorProductCode","customProductCode","quantity","unitPrice","item_tax","item_discount","cost","unitOfMeasure","description","comments","item_costCenter","item_costCenterDesc","costCenterSeq","costCenterRoute","item_release","configID","bundleID","invLoc","InventoryLocation","id","seq","Name","ship_address1","ship_address2","address3","ship_city","ship_state","ship_postalCode","ship_country","email","phoneNumber","faxNumber"};
		String[] attrValues={consumerName,consumerTransactionId,consumerTransactionDateTime,firstName,middleName,lastName,cardHighValueToken,expirationDate,cardType,amount,applicationTransactionNumber,name,bill_address1,bill_address2,bill_city,bill_state,bill_postalCode,bill_country,orderType,brandType,orderSource,customerOrderNumber,originalOrderNumber,orderDate,acc_costCenter,acc_costCenterDesc,poNumber,acc_release,serialNumber,subTotal,totals_tax,delivery,totals_discount,misc,total,lineNumber,sku,odSku,customerSku,vendorProductCode,customProductCode,quantity,unitPrice,item_tax,item_discount,cost,unitOfMeasure,description,comments,item_costCenter,item_costCenterDesc,costCenterSeq,costCenterRoute,item_release,configID,bundleID,invLoc,InventoryLocation,id,seq,Name,ship_address1,ship_address2,address3,ship_city,ship_state,ship_postalCode,ship_country,email,phoneNumber,faxNumber};
		String templateReplacedBody = objCommonServiceLibraries.replaceTemplateWithValues(body,createContextForReplacement(attrNames,attrValues));
		propertiesContainer.setBodyOrEnvelope(templateReplacedBody);
		System.out.println(templateReplacedBody.toString());
		//Build service container and get response
		objServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = objServiceHandler.getServiceAttributesContainer();
		Response response = container.getResponse();
		response.prettyPrint();
	}
	
	
}
