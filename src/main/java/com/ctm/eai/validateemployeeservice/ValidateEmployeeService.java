package com.ctm.eai.validateemployeeservice;

public interface ValidateEmployeeService {  

	public static final String NODE_CONSUMER_NAME = "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.consumer.consumerName";
	public static final String NODE_CONSUMER_TXN_ID = "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.consumer.consumerTransactionID";
	public static final String NODE_ODEMPLOYEE_ID= "Envelope.Body.ValidateEmployeeResponseABM.purchaseOrderResponse.requestStatus.statusDescription";
	public static final String NODE_CORELATION_ID= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.correlationID";
	public static final String NODE_TIME_RECEIVED= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.timeReceived";
	
	
	public static final String NODE_SERVICE_NAME= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.service.serviceName";
	public static final String NODE_SERVICE_OPERATION= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.service.serviceOperation";
	public static final String NODE_CONTAINS_SENSITIVE_DATA= "Envelope.Body.ValidateEmployeeResponseABM.transactionHeader.serviceBus.document.containsSensitiveData";
	public static final String NODE_RESULT= "Envelope.Body.ValidateEmployeeResponseABM.ValidateEmployeeResponse.Result";
	public static final String NODE_FIRST_NAME= "Envelope.Body.ValidateEmployeeResponseABM.ValidateEmployeeResponse.FirstName";
	public static final String NODE_LAST_NAME= "Envelope.Body.ValidateEmployeeResponseABM.ValidateEmployeeResponse.LastName";
	
	
	public static final String NODE_FAULT_STRING = "Envelope.Body.Fault.faultstring";
	public static final String NODE_ERROR_CODE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorCode";
	public static final String NODE_ERROR_MESSAGE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorMessage";
	
	
	public static final String REASON_CODE = "Employee ID not found at PeopleSoft  for Employee ID: 0";
	public static final String ERROR_CODE = "100";
	public static final String ERROR_MESSAGE = "Employee ID not found at PeopleSoft  for Employee ID: 0";
	public static final Integer CORRELATIONID_LENGTH =36;
}



