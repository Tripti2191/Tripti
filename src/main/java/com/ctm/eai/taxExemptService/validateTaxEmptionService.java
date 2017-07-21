//All the nodes.

package com.ctm.eai.taxExemptService;

public interface validateTaxEmptionService {  

	public static final String NODE_CONSUMER_NAME = "Envelope.Body.ValidationResponse.transactionHeader.consumer.consumerName";
	public static final String NODE_CONSUMER_TXN_ID = "Envelope.Body.ValidationResponse.transactionHeader.consumer.consumerTransactionID";
	public static final String NODE_State_Of_Request = "Envelope.Body.ValidationResponse.stateOfRequest";
	public static final String NODE_Tax_Exempt_ID = "Envelope.Body.ValidationResponse.taxExemptID";
	public static final String NODE_ODEMPLOYEE_ID= "Envelope.Body.testValidationTaxRequestABM.purchaseOrderResponse.requestStatus.statusDescription";
	public static final String NODE_CORELATION_ID= "Envelope.Body.testValidationTaxRequestABM.transactionHeader.serviceBus.correlationID";
	public static final String NODE_TIME_RECEIVED= "Envelope.Body.testValidationTaxRequestABM.transactionHeader.serviceBus.timeReceived";
	
	
	public static final String NODE_SERVICE_NAME= "Envelope.Body.testValidationTaxRequestABM.transactionHeader.serviceBus.service.serviceName";
	public static final String NODE_SERVICE_OPERATION= "Envelope.Body.testValidationTaxRequestABM.transactionHeader.serviceBus.service.serviceOperation";
	public static final String NODE_CONTAINS_SENSITIVE_DATA= "Envelope.Body.testValidationTaxRequestABM.transactionHeader.serviceBus.document.containsSensitiveData";
	public static final String NODE_RESULT= "Envelope.Body.testValidationTaxRequestABM.ValidateEmployeeResponse.Result";
	public static final String NODE_FIRST_NAME= "Envelope.Body.testValidationTaxRequestABM.ValidateEmployeeResponse.FirstName";
	public static final String NODE_LAST_NAME= "Envelope.Body.testValidationTaxRequestABM.ValidateEmployeeResponse.LastName";
	
	
	public static final String NODE_FAULT_STRING = "Envelope.Body.Fault.faultstring";
	public static final String NODE_ERROR_CODE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorCode";
	public static final String NODE_ERROR_MESSAGE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorMessage";
	
	public static final String NODE_NULLEMPID_FAULT_STRING = "Envelope.Body.Fault.faultstring";
	public static final String NODE_NULLEMPID_REASON = "Envelope.Body.Fault.detail.fault.reason";
	public static final String NODE_NULLEMPID_ERROR_CODE= "Envelope.Body.Fault.detail.fault.errorCode";
	
	public static final String NODE_BIGID_FAULT_STRING = "Envelope.Body.Fault.faultstring";
	public static final String NODE_BIGID_ERROR_CODE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorCode";
	public static final String NODE_BIGID_ERROR_MESSAGE = "Envelope.Body.Fault.detail.ValidateEmployeeFaultMessage.ErrorMessage";
	
	public static final String EMPIDNULL_REASON_CODE = "BEA-382513: OSB Replace action failed updating variable \"body\": Error parsing XML: {err}FORG0005: Expected exactly one item, got 0 items";
	public static final String EMPIDNULL_ERROR_CODE = "BEA-382513";
	public static final String EMPIDNULL_ERROR_MESSAGE = "OSB Replace action failed updating variable \"body\": Error parsing XML: {err}FORG0005: Expected exactly one item, got 0 items";
	
	public static final String ERROR_CODE = "100";
	public static final Integer CORRELATIONID_LENGTH =36;
	public static final String BIGID_ERROR_CODE ="8";
}



