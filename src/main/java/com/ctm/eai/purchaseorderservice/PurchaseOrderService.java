package com.ctm.eai.purchaseorderservice;

public interface PurchaseOrderService {

	public static final String NODE_CONSUMER_NAME =   "Envelope.Body.createPurchaseOrderResponse.transactionHeader.consumer.consumerName";
	public static final String NODE_CONSUMER_TXN_ID = "Envelope.Body.createPurchaseOrderResponse.transactionHeader.consumer.consumerTransactionID";
	public static final String NODE_REQUEST_ID = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.senderRequestID.requestID";
	public static final String NODE_STATUS_CODE = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusCode";
	public static final String NODE_STATUS_DESCRIPTION = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusDescription";
	
	public static final String NODE_SHIPMENT_DATE= "Envelope.Body.createPurchaseOrderResponse.purchaseOrder.purchaseOrderHeader.delivery.scheduled.shipmentDate";
	public static final String NODE_ARRIVAL_DATE= "Envelope.Body.createPurchaseOrderResponse.purchaseOrder.purchaseOrderHeader.delivery.scheduled.arrivalDate";
	
	public static final String NODE_GET_CONSUMER_NAME = "Envelope.Body.getPurchaseOrderStatusResponse.transactionHeader.consumer.consumerName";
	public static final String NODE_GET_CONSUMER_TXN_ID = "Envelope.Body.getPurchaseOrderStatusResponse.transactionHeader.consumer.consumerTransactionID";
	public static final String NODE_GET_STATUS_CODE = "Envelope.Body.getPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusCode";
	public static final String NODE_GET_STATUS_DESCRIPTION = "Envelope.Body.getPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusDescription";
	public static final String NODE_GET_LINE_STATUS= "Envelope.Body.getPurchaseOrderStatusResponse.senderRequestID.requiredLineStatus";
	
	
	
	
	public static final String NODE_ERROR_CODE = "Envelope.Body.Fault.detail.fault.errorCode";
	public static final String NODE_ERROR_REASON = "Envelope.Body.Fault.detail.fault.reason";
	public static final String NODE_ERROR_MESSAGE = "Envelope.Body.Fault.detail.fault.details.ValidationFailureDetail.message";
	
	
	

	public static final String SUCCESS_STRING = "SUCCESS";
	public static final String SUCCESS_MESSAGE = "SUCCESSFULLY_PUBLISHED_TO_MQ";
	public static final String REASON_CODE = "OSB Validate action failed validation";
	public static final String ERROR_CODE = "BEA-382505";
}



