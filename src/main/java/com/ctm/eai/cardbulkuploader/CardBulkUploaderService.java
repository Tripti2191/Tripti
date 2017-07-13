package com.ctm.eai.cardbulkuploader;

public interface CardBulkUploaderService {
	
	public static final String NODE_CONSUMER_NAME =   "Envelope.Body.uploadProCardsResponse.transactionHeader.consumer.consumerName";
	public static final String NODE_CONSUMER_TXN_ID = "Envelope.Body.uploadProCardsResponse.transactionHeader.consumer.consumerTransactionID";
	
	public static final String NODE_correlationID ="Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.correlationID";
	public static final String NODE_TIME_RECEIVED = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.timeReceived";
	public static final String NODE_SERVICE_NAME = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.service.serviceName";
	public static final String NODE_SERVICE_OPERATION = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.service.serviceOperation";
	public static final String NODE_SERVICE_VERSION = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.service.serviceVersion";
	public static final String NODE_CONTAINS_SENSITIVE_DATA = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.document.containsSensitiveData";
	public static final String NODE_CONSUMERISESB = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.routing.consumerIsESB";
	public static final String NODE_KEY = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.invocation.headerFields.keyValuePairs.key";
	public static final String NODE_VALUE = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.invocation.headerFields.keyValuePairs.value";
	public static final String NODE_PHYSICAL_HOST = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.environment.physicalHost";
	public static final String NODE_SERVER_NAME = "Envelope.Body.uploadProCardsResponse.transactionHeader.serviceBus.environment.serverName";
	
	
	public static final String NODE_BILL_TO = "Envelope.Body.uploadProCardsResponse.headerInfo.billTo";
	public static final String NODE_LOGIN_ID = "Envelope.Body.uploadProCardsResponse.headerInfo.loginId";
	public static final String NODE_EMAIL_ADDRESS = "Envelope.Body.uploadProCardsResponse.headerInfo.emailAddress";
	public static final String NODE_BATCHID = "Envelope.Body.uploadProCardsResponse.headerInfo.batchId";
	public static final String NODE_FILE_UPLOAD_TEMPLATE_TYPE = "Envelope.Body.uploadProCardsResponse.headerInfo.fileUploadTemplateType";
	public static final String NODE_TOTAL_NO_OF_SEGMENTS= "Envelope.Body.uploadProCardsResponse.headerInfo.totalNumberOfSegments";
	public static final String NODE_ROWS_INCURRENT_SEGMENTS = "Envelope.Body.uploadProCardsResponse.headerInfo.numberOfRowsInCurrentSegment";
	public static final String NODE_SEGMENTID = "Envelope.Body.uploadProCardsResponse.headerInfo.segmentId";
	public static final String NODE_UPLOAD_TIMESTAMP = "Envelope.Body.uploadProCardsResponse.headerInfo.uploadTimestamp";
	
	
	public static final String NODE_STATUS_CODE = "Envelope.Body.uploadProCardsResponse.cardUploadStatus.statusCode";
	public static final String NODE_STATUS_DESCRIPTION = "Envelope.Body.uploadProCardsResponse.cardUploadStatus.statusDescription";
	
	
}
