package com.ctm.eai.validateemployeeservice;



public interface ValidateEmployeeService {  

	public static final String NODE_CONSUMER_NAME = "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.consumer.consumerName";
	public static final String NODE_CONSUMER_TXN_ID = "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.consumer.consumerTransactionID";
	public static final String NODE_ODEMPLOYEE_ID= "Envelope.Body.ValidateEmployeeResponseABM.purchaseOrderResponse.requestStatus.statusDescription";
	public static final String NODE_CORELATION_ID= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.correlationID";
	public static final String NODE_TIME_RECEIVED= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.timeReceived";
	//public static final String NODE_REQUEST_ID = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.senderRequestID.requestID";
	//public static final String NODE_STATUS_CODE = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusCode";
	//public static final String NODE_STATUS_DESCRIPTION = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusDescription";
	//public static final String NODE_LINE_STATUS= "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusDescription";
	
	public static final String NODE_SERVICE_NAME= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.service.serviceName";
	public static final String NODE_SERVICE_OPERATION= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.service.serviceOperation";
	public static final String NODE_CONTAINS_SENSITIVE_DATA= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.document.containsSensitiveData";
	public static final String NODE_RESULT= "Envelope.Body.ValidateEmployeeResponseABM.ValidateEmployeeResponse.Result";
	public static final String NODE_FIRST_NAME= "Envelope.Body.ValidateEmployeeResponseABM.ValidateEmployeeResponse.FirstName";
	public static final String NODE_LAST_NAME= "Envelope.Body.ValidateEmployeeResponseABM.ValidateEmployeeResponse.LastName";
	
	
	public static final String NODE_FAULT_STRING = "Envelope.Body.Fault.detail.fault.faultstring";
	public static final String NODE_ERROR_CODE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorCode";
	public static final String NODE_ERROR_MESSAGE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorMessage";
	
	
	
	

    
	public static final String SUCCESS_STRING = "SUCCESS";
	public static final String SUCCESS_MESSAGE = "SUCCESSFULLY_PUBLISHED_TO_MQ";
	public static final String REASON_CODE = "Employee ID not found at PeopleSoft  for Employee ID";
	public static final String ERROR_CODE = "100";
}



