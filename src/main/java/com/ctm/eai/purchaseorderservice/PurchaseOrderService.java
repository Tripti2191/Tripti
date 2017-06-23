package com.ctm.eai.purchaseorderservice;

public interface PurchaseOrderService {

	public static final String CONSUMER_NAME = "Envelope.Body.createPurchaseOrderResponse.transactionHeader.consumer.consumerName";
	public static final String CONSUMER_TXN_ID = "Envelope.Body.createPurchaseOrderResponse.transactionHeader.consumer.consumerTransactionID";
	public static final String REQUEST_ID = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.senderRequestID.requestID";
	public static final String STATUS_CODE = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusCode";
	public static final String STATUS_DESCRIPTION = "Envelope.Body.createPurchaseOrderResponse.purchaseOrderResponse.requestStatus.statusDescription";

	public static final String SUCCESS_STRING = "SUCCESS";
	public static final String SUCCESS_MESSAGE = "SUCCESSFULLY_PUBLISHED_TO_MQ";
}
