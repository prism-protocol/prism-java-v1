package com.serverless;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.data.DeviceModel;
import com.serverless.data.Devices;
import com.serverless.db.DynamoDBAdapter;

public class GetAllDevices implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(GetAllDevices.class);
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		List<Devices> tx;
		
		  try {
		      /*Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
		      String accountId = pathParameters.get("account_id");
		      LOG.info("Getting transactions for " + accountId);*/
		      tx = DynamoDBAdapter.getInstance().getAllDevices();
		    } catch (Exception e) {
		      LOG.error(e, e);
		      Response responseBody = new Response("Failure getting transactions", input);
		      return ApiGatewayResponse.builder()
		      .setStatusCode(500)
		      .setObjectBody(responseBody)
		      .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		      .build();
		   }
		    
		    DeviceModel rm = new DeviceModel();
		    rm.setResponse(3);
		    rm.setMessage("Devices List Fetched");
		    rm.setDeviceData(tx);
		    return ApiGatewayResponse.builder()
		    .setStatusCode(200)
		    .setObjectBody(rm)
		    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
		    .build();
		}
}