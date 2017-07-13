package com.ctm.eai.cardbulkuploader;

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
 * Test to create CardBulk through SOAP
 * 
 * @author praveen-bhasker
 *
 */
@Listeners({ com.ctm.report.CustomReport.class })
public class CardBulkUploader extends BaseCardBulkUploaderTestLibrary implements CardBulkUploaderService {

	@Test(dataProviderClass = ServicesDataProvider.class, dataProvider = "Service_DataFeed_Provider")
	@ServiceDataFile("ServiceData/CardBulkUploader/CardBulkUploader.txt")
	public void testCardBulkUploader(String index, String sceanrioName, String consumerName,
			String consumerTransactionID, String billTo, String loginId, String emailAddress, String batchId,
			String fileUploadTemplateType, String totalNumberOfSegments, String numberOfRowsInCurrentSegment,
			String segmentId, String uploadTimestamp, String rowNum, String action, String cardNo, String cardDesc,
			String desktop, String po, String department, String release, String addressId, String correlationId,
			String timeReceived, String serviceName, String serviceOperation, String serviceVersion,
			String containsSensitivedata, String consumerIsesb, String key, String value, String physicalHost,
			String serverName, String statusCode, String statusDescription, String expectedResult) throws IOException {

		consoleReport.logTestMessage("Starting test scenario: " + sceanrioName);

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
		String body = xmlServiceLibrary.getRequestBodyFromFile("ServiceData/CardBulkUploader/CardBulkUploader.xml");
		String templateReplacedBody = xmlServiceLibrary.replaceTemplateWithValues(body,
				createContextForReplacement(consumerName, consumerTransactionID, billTo, loginId, emailAddress, batchId,
						fileUploadTemplateType, totalNumberOfSegments, numberOfRowsInCurrentSegment, segmentId,
						uploadTimestamp, rowNum, action, cardNo, cardDesc, desktop, po, department, release,
						addressId));
		propertiesContainer.setBodyOrEnvelope(templateReplacedBody);

		// Build service container and get response
		xmlServiceHandler.buildServiceContainer(propertiesContainer);
		ServiceAttributesContainer container = xmlServiceHandler.getServiceAttributesContainer();
		Response response = container.getResponse();
		CommonServiceLibraries serviceLibrary = new CommonServiceLibraries();
		response.prettyPrint();
		xmlServiceVerificationLibraries.verifyStatusCode(response, 200);

		// Validation part
		if (expectedResult.equalsIgnoreCase("PASS")) {
			xmlServiceVerificationLibraries.verifyStatusCode(response, 200);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_NAME,
					consumerName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMER_TXN_ID,
					consumerTransactionID);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_correlationID,
					correlationId);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_TIME_RECEIVED,
					timeReceived);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_SERVICE_NAME,
					serviceName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_SERVICE_OPERATION,
					serviceOperation);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_SERVICE_VERSION,
					serviceVersion);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONTAINS_SENSITIVE_DATA,
					containsSensitivedata);

			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_CONSUMERISESB,
					serviceName);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_KEY, key);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_VALUE, value);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_PHYSICAL_HOST,
					physicalHost);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_SERVER_NAME,
					serverName);

			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_BILL_TO, billTo);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_LOGIN_ID, loginId);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_EMAIL_ADDRESS,
					emailAddress);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_BATCHID, batchId);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response,
					NODE_FILE_UPLOAD_TEMPLATE_TYPE, fileUploadTemplateType);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_TOTAL_NO_OF_SEGMENTS,
					totalNumberOfSegments);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_ROWS_INCURRENT_SEGMENTS,
					numberOfRowsInCurrentSegment);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_SEGMENTID, segmentId);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_UPLOAD_TIMESTAMP,
					uploadTimestamp);

			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_STATUS_CODE,
					statusCode);
			xmlServiceVerificationLibraries.verifyStringFromResponseValueIsEqual(response, NODE_STATUS_DESCRIPTION,
					statusDescription);
		}

	}

	private VelocityContext createContextForReplacement(String consumerName, String consumerTransactionID,
			String billTo, String loginId, String emailAddress, String batchId, String fileUploadTemplateType,
			String totalNumberOfSegments, String numberOfRowsInCurrentSegment, String segmentId, String uploadTimestamp,
			String rowNum, String action, String cardNo, String cardDesc, String desktop, String po, String department,
			String release, String addressId) {
		VelocityContext context = new VelocityContext();
		context.put("$CONSUMER_NAME", consumerName);
		context.put("$CONSUMER_TRANSACTIONID", consumerTransactionID);
		context.put("$BILL_TO", billTo);
		context.put("$LOGIN_ID", loginId);
		context.put("$EMAIL_ADDRESS", emailAddress);
		context.put("$BATCH_ID", batchId);
		context.put("$FILE_UPLOAD_TEMPLATE_TYPE", fileUploadTemplateType);
		context.put("$TOTAL_NUMBER_OF_SEGMENTS", totalNumberOfSegments);
		context.put("$NUMBER_OF_ROWS_INCURRENT_SEGMENT", numberOfRowsInCurrentSegment);
		context.put("$SEGMENT_ID", segmentId);
		context.put("$UPLOAD_TIMESTAMP", uploadTimestamp);
		context.put("$ROW_NUM", rowNum);
		context.put("$ACTION", action);
		context.put("$CARDNO", cardNo);
		context.put("$CARD_DESC", cardDesc);
		context.put("$DESKTOP", desktop);
		context.put("$PO", po);
		context.put("$DEPARTMENT", department);
		context.put("$RELEASE", release);
		context.put("$ADDRESSID", addressId);
		return context;
	}
}
