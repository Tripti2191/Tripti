package com.ctm.eai.serviceorderstatusinquiry;

public interface ServiceOrderStatusInquiryLibrary {
	public static final String NODE_MESSAGEID = "Envelope.Header.MessageID";
	public static final String NODE_REPLYTO_ADDRESS= "Envelope.Header.ReplyTo.Address";
	public static final String NODE_REPLYTO_COMPOSITE_INSTANCE_CREATED_TIME ="Envelope.Header.ReplyTo.ReferenceParameters.tracking.compositeInstanceCreatedTime";
	public static final String NODE_FAULTTO_ADDRESS ="Envelope.Header.FaultTo.Address";
	public static final String NODE_FAULTTO_COMPOSITE_INSTANCE_CREATED_TIME ="Envelope.Header.FaultTo.ReferenceParameters.tracking.compositeInstanceCreatedTime";
	public static final String NODE_SERVICE_REQUEST_ELEMENT ="Envelope.Body.searchServicerequestElement ";
	public static final String NODE_XRETURNSTATUS_INOUT="Envelope.Header.Body.searchServicerequestElement.xReturnStatus_inout";
	
	}




