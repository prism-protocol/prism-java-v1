package com.serverless.db;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.serverless.data.Devices;
import com.serverless.data.UserNotifications;
import com.serverless.data.Users;

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
    public void putUsers(Users users) throws IOException{
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(users);
    }
    public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
    
    public void updateUser(Users users) throws IOException{
    	
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(users);
    }
    
    public List<UserNotifications> getNotifications() throws IOException {
    	DynamoDBMapper mapper = new DynamoDBMapper(client);
    	return mapper.scan(UserNotifications.class, new DynamoDBScanExpression());
    }
}
