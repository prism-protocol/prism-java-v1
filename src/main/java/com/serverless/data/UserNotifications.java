package com.serverless.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "userNotifications")
public class UserNotifications {
	 @DynamoDBHashKey(attributeName = "notificationId")
	    String notificationId; //Hash Key
	 @DynamoDBAttribute(attributeName = "body")
	    String body;
	 @DynamoDBAttribute(attributeName = "description")
	    String description;
	 @DynamoDBAttribute(attributeName = "sender")
	    String sender;
	 @DynamoDBAttribute(attributeName = "team")
	    String team;
	 @DynamoDBAttribute(attributeName = "testResultId")
	    String testResultId;
	 @DynamoDBAttribute(attributeName = "url")
	    String url;
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(String testResultId) {
		this.testResultId = testResultId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
