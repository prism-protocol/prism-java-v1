package com.serverless;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.data.NotificationModel;
import com.serverless.data.UserNotifications;
import com.serverless.db.DynamoDBAdapter;

public class UserNotificationHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(GetAllDevices.class);
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		List<UserNotifications> tx;
		
		  try {
			  tx = DynamoDBAdapter.getInstance().getNotifications();
		    } catch (Exception e) {
		      LOG.error(e, e);
		      NotificationModel rm = new NotificationModel();
			    rm.setResponse(0);
			    rm.setMessage("something went wrong");
			   return ApiGatewayResponse.builder()
		      .setStatusCode(500)
		      .setObjectBody(rm)
		      .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		      .build();
		   }
		    
		  NotificationModel rm = new NotificationModel();
		    rm.setResponse(3);
		    rm.setMessage("Notifications List Fetched");
		    rm.setNotificationList(tx);
		    return ApiGatewayResponse.builder()
		    .setStatusCode(200)
		    .setObjectBody(rm)
		    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		    .build();
		}
}
