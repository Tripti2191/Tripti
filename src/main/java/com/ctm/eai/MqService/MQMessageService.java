package com.ctm.eai.MqService;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.jms.JMSMessage;
import com.ibm.jms.JMSTextMessage;
import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnection;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueReceiver;
import com.ibm.mq.jms.MQQueueSender;
import com.ibm.mq.jms.MQQueueSession;
import com.ibm.msg.client.wmq.WMQConstants;


public class MQMessageService {

	private static final String HOST_NAME = "future.officedepot.com";
	private static final Integer PORT_NO = 1414;
	private static final String QUEUE_MANAGER = "FUTURE";
	private static final String CHANNEL = "SOA_FUTURE";
	private MQQueueConnectionFactory cf;
	private MQQueueConnection connection;
	private MQQueueSession session;
	private MQQueue queue;
	private MQQueueSender sender;
	MQMessageService() throws JMSException{
	        cf = new MQQueueConnectionFactory();
	        cf.setHostName(HOST_NAME);
	        cf.setPort(PORT_NO); 
	        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,WMQConstants.WMQ_CM_CLIENT);
	        cf.setQueueManager(QUEUE_MANAGER);
	        cf.setChannel(CHANNEL);

	}
	
	public void sentMQMemssage(String message) throws JMSException{
       // connection = (MQQueueConnection)cf.createQueueConnection("cdh","cdh");
		
		 connection = (MQQueueConnection)cf.createQueueConnection();
        session =(MQQueueSession)connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
        queue =(MQQueue)session.createQueue("queue:///IPS_SOA_MRM_COUPON_DATA_DEV");
        sender = (MQQueueSender)session.createSender(queue);
        TextMessage textMessage =(TextMessage)session.createTextMessage(message);
        connection.start();
        sender.send(textMessage);
        System.out.println("Sent message to Queue MyTestQueue: " + textMessage.getText());
        sender.close();
        session.close();
        connection.close();
}

	public static void main(String arg[]){
		try{
		MQMessageService lMQMessageService= new MQMessageService();
		lMQMessageService.sentMQMemssage("Hello -- Test message");
		} catch ( Exception lException){
			lException.printStackTrace();
		}
	}
}

