package com.serverless;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.data.TransactionWallet;
import com.serverless.data.WalletModel;
import com.serverless.db.DynamoDBAdapter;

public class WalletTransaction implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(WalletTransaction.class);
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
	    LOG.info("received: " + input);
	    //lets get our path parameter for account_id
	    WalletModel wm = null;
	    WalletModel rm = null; 
	    try{
	      ObjectMapper mapper = new ObjectMapper();
	      JsonNode body = mapper.readTree((String) input.get("body"));
	      float points = (float) body.get("points").asDouble();
	      String title = body.get("rewardType").asText();
	      String pointsfor = body.get("pointsFor").asText();
	      String email = body.get("emailid").asText();
	      String timestamp = body.get("timeStamp").asText();
	      String userid = body.get("userId").asText();
	      TransactionWallet tw = new TransactionWallet();
	      tw.setEmailid(email);
	      tw.setPoints(points);
	      tw.setPointsFor(pointsfor);
	      tw.setTimeStamp(timestamp);
	      tw.setTitle(title);
	      tw.setUserId(userid);
	      wm = DynamoDBAdapter.getInstance().insertNewWallet(tw);
	      rm = new WalletModel();
	      if(wm.getResponse() == 3) {
	    	rm.setResponse(3);
	  	    rm.setMessage("points successfully shared");
	      }else {
	    	  rm.setMessage("user mailid not available to transfer points");
      		  rm.setResponse(0);
	      }
	    } catch(Exception e){
	      LOG.error(e,e);
	      return ApiGatewayResponse.builder()
	      .setStatusCode(500)
	      .setObjectBody(e)
	      .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
	      .build();
	    }
	    return ApiGatewayResponse.builder()
	    .setStatusCode(200)
	    .setObjectBody(rm)
	    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
	    .build();
	}
}

