package com.serverless;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.data.NotificationModel;
import com.serverless.db.DynamoDBAdapter;

public class UserNotificationHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(UserNotificationHandler.class);
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		Map<String, Object> tx;
		NotificationModel rm = new NotificationModel();
		  try {
			  ObjectMapper mapper = new ObjectMapper();
			  mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		      JsonNode body = mapper.readTree((String) input.get("body"));
		      String userid = body.get("userId").asText();
		      
			  tx = DynamoDBAdapter.getInstance().getNotifications(userid);
			  System.out.println("transobj.."+tx);
		    } catch (Exception e) {
		      LOG.error(e, e);
		      
			    rm.setResponse(0);
			    rm.setMessage("something went wrong");
			    
			   return ApiGatewayResponse.builder()
		      .setStatusCode(500)
		      .setObjectBody(rm)
		      .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		      .build();
		   }
		    rm.setResponse(3);
		    rm.setMessage("Notifications List Fetched");
		    rm.setData(tx);
		    return ApiGatewayResponse.builder()
		    .setStatusCode(200)
		    .setObjectBody(rm)
		    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		    .build();
		}
}
