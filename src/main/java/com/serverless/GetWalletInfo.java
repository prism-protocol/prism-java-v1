package com.serverless;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.data.DeviceModel;
import com.serverless.data.WalletModel;
import com.serverless.db.DynamoDBAdapter;

public class GetWalletInfo implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(GetWalletInfo.class);
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		Map<String, Object> tx;
		
		  try {
			  ObjectMapper mapper = new ObjectMapper();
			  mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		      JsonNode body = mapper.readTree((String) input.get("body"));
		      String username = body.get("userName").asText();
		      tx = DynamoDBAdapter.getInstance().getWallet(username);
		    } catch (Exception e) {
		      LOG.error(e, e);
		        WalletModel rm = new WalletModel();
			    rm.setResponse(0);
			    rm.setMessage("something went wrong");
			   return ApiGatewayResponse.builder()
		      .setStatusCode(500)
		      .setObjectBody(rm)
		      .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		      .build();
		   }
		    
		    WalletModel rm = new WalletModel();
		    rm.setResponse(3);
		    rm.setMessage("user wallet List Fetched");
		    rm.setData(tx);
		    return ApiGatewayResponse.builder()
		    .setStatusCode(200)
		    .setObjectBody(rm)
		    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		    .build();
		}
}
