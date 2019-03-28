package com.serverless.db;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.serverless.data.Devices;
import com.serverless.data.TransactionWallet;
import com.serverless.data.Users;
import com.serverless.data.WalletModel;

public class DynamoDBAdapter {
	
	private Logger logger = LogManager.getLogger(this.getClass());

    private final static DynamoDBAdapter adapter = new DynamoDBAdapter();

    private final AmazonDynamoDB client;

    private DynamoDBAdapter() {
        client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration("https://dynamodb.cn-north-1.amazonaws.com.cn", "cn-north-1"))
                .build();
        logger.info("Created DynamoDB client");
    }

    public static DynamoDBAdapter getInstance(){
        return adapter;
    }

    
    public List<Devices> getAllDevices() throws IOException {
    	DynamoDBMapper mapper = new DynamoDBMapper(client);
    	return mapper.scan(Devices.class, new DynamoDBScanExpression());
		
    	
    }
    /* ==================== inserting user module===================*/ 
    public WalletModel putUsers(Users users) throws IOException{
    	  // List<String> email = new ArrayList<>();
           DynamoDBMapper mapper = new DynamoDBMapper(client);
           DynamoDB dynamoDB = new DynamoDB(client);
           Map<String, String> attributeNames = new HashMap<String, String >();
           attributeNames.put("#email", "email");
           Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
           attributeValues.put(":from", new AttributeValue().withS(users.getEmail()));
    
           ScanRequest scanRequest = new ScanRequest()
                   .withTableName("Users")
                   .withFilterExpression("#email = :from")
                   .withExpressionAttributeNames(attributeNames)
                   .withExpressionAttributeValues(attributeValues)
                   .withProjectionExpression("userId");
    	   	   ScanResult scanResult = client.scan(scanRequest);
        	   List<Map<String,AttributeValue>> attribute = scanResult.getItems();
        	    System.out.println("items list..."+scanResult);
    	    	WalletModel wm = new WalletModel();
        	    if(attribute.size()>0) {
        	    	System.out.println("user already registered with us...");
        	    	wm.setResponse(0);
        	    	wm.setMessage("user already registered with us");
        	    	        	    	
        	    }else {
        	    	Table table = dynamoDB.getTable("Wallet");
        	       	  Map<String, Object> WalletInfo = new HashMap<String, Object>();
        	           		   WalletInfo.put("points", 50.00);
        	           		   WalletInfo.put("pointsFor", "New registration with Prism");
        	           		   WalletInfo.put("pointsType", "Credit");
        	           		   WalletInfo.put("rewardType", "Prism Reward points");
        	           		   long ut = System.currentTimeMillis() / 1000;
        	           		   WalletInfo.put("timeStamp", ut);
        	           		   //ValueMap map = new ValueMap().withList(":WalletInfo", Arrays.asList(WalletInfo));
        	           		   
        	        Item item = new Item().withPrimaryKey("userName", users.getEmail())
        	                .withList("WalletInfo", WalletInfo);  
        	            table.putItem(item);
        	            mapper.save(users);
        	           	wm.setResponse(3);
            	    	wm.setMessage("Your registered success");
              	    }
				return wm;
    }
    
    public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
    
    public void updateUser(Users users) throws IOException{
    	
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(users);
    }
    
    public Map<String, Object> getNotifications(String userid) throws IOException {
    	DynamoDB dynamoDB = new DynamoDB(client);
    	Table table = dynamoDB.getTable("userNotifications");
    	GetItemSpec spec = new GetItemSpec()
    			.withPrimaryKey("userId", userid)
    			.withConsistentRead(true);
    	Item item = table.getItem(spec);
    	Map<String, Object> vals = new HashMap<>();
        vals.put("notificationData", item.get("notificationData"));
        return vals;
    }
       public void updateNotification(String userid,boolean status) throws IOException{
    	DynamoDB dynamoDB = new DynamoDB(client);
       	Table table = dynamoDB.getTable("userNotifications");
       	Item item = new Item() 
                .withPrimaryKey("userId", userid) 
                .withStringSet( "notificationData",
                new HashSet<String>(Arrays.asList("status"))) 
                .withBoolean("status", true);
                table.putItem(item);  
        
    }
       public Map<String, Object> getWallet(String username) throws IOException {
       	DynamoDB dynamoDB = new DynamoDB(client);
       	Table table = dynamoDB.getTable("Wallet");
       	GetItemSpec spec = new GetItemSpec()
       			.withPrimaryKey("userName", username)
       			.withConsistentRead(true);
       	Item item = table.getItem(spec);
       	Map<String, Object> vals = new HashMap<>();
       	vals.put("WalletInfo", item.get("WalletInfo"));
           return vals;
       }
       
      
	public WalletModel insertNewWallet(TransactionWallet trans) throws IOException {
    	   DynamoDB dynamoDB = new DynamoDB(client);
    	   Map<String, String> attributeNames = new HashMap<String, String >();
           attributeNames.put("#email", "email");
           Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
           attributeValues.put(":from", new AttributeValue().withS(trans.getEmailid()));
    
           ScanRequest scanRequest = new ScanRequest()
                   .withTableName("Users")
                   .withFilterExpression("#email = :from")
                   .withExpressionAttributeNames(attributeNames)
                   .withExpressionAttributeValues(attributeValues)
                   .withProjectionExpression("userId");
    	   	   ScanResult scanResult = client.scan(scanRequest);
        	   List<Map<String,AttributeValue>> attribute = scanResult.getItems();
        	    System.out.println("items list..."+scanResult);
        	   WalletModel wm = new WalletModel();
        	   if(attribute.size()>0) {
        		   System.out.println("email id found..");
           		   Table wallet = dynamoDB.getTable("Wallet");
           		   Map<String, Object> WalletInfo = new HashMap<String, Object>();
           		   WalletInfo.put("points", trans.getPoints());
           		   WalletInfo.put("pointsFor", trans.getPointsFor());
           		   WalletInfo.put("pointsType", "Credit");
           		   WalletInfo.put("rewardType", trans.getTitle());
           		   WalletInfo.put("timeStamp", trans.getTimeStamp());
           		   ValueMap map = new ValueMap().withList(":WalletInfo", Arrays.asList(WalletInfo));
           		   UpdateItemSpec updateItemSpec = new UpdateItemSpec()
           		            .withPrimaryKey("userName", trans.getEmailid())
           		            .withUpdateExpression("set WalletInfo = list_append(WalletInfo, :WalletInfo )")
           		            .withValueMap(map);

           		UpdateItemOutcome result = wallet.updateItem(updateItemSpec);
           		System.out.println("PutItem succeeded:\n" + result.getUpdateItemResult());
           	
           		DynamoDB dynamoDB1 = new DynamoDB(client);
            	   Table table1 = dynamoDB1.getTable("Users");
            	   GetItemSpec spec1 = new GetItemSpec()
            			    .withPrimaryKey("userId", trans.getUserId())
                  			.withProjectionExpression("email")
                  			.withConsistentRead(true);
            	   Item item1 = table1.getItem(spec1);
            	   System.out.println("email id..."+item1.getString("email"));
            	  Map<String, Object> WalletInfo1 = new HashMap<String, Object>();
       		   WalletInfo1.put("points", trans.getPoints());
       		   WalletInfo1.put("pointsFor", trans.getPointsFor());
       		   WalletInfo1.put("pointsType", "Debit");
       		   WalletInfo1.put("rewardType", trans.getTitle());
       		   WalletInfo1.put("timeStamp", trans.getTimeStamp());
       		   ValueMap map1 = new ValueMap().withList(":WalletInfo1", Arrays.asList(WalletInfo1));
       		   UpdateItemSpec updateItemSpec1 = new UpdateItemSpec()
       		            .withPrimaryKey("userName", item1.getString("email"))
       		            .withUpdateExpression("set WalletInfo = list_append(WalletInfo, :WalletInfo1 )")
       		            .withValueMap(map1);
       		   UpdateItemOutcome result1 = wallet.updateItem(updateItemSpec1);
       		   System.out.println("PutItem succeeded:\n" + result1.getUpdateItemResult());
       		   wm.setMessage("points shared successfully");
     		   wm.setResponse(3);
           	   }else {
           		   wm.setMessage("user mailid not available to transfer points");
           		   wm.setResponse(0);
           	   }
			return wm;
       }
}
