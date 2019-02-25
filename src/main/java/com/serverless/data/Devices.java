package com.serverless.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Devices")
public class Devices {
	 @DynamoDBHashKey(attributeName = "deviceId")
	    String deviceId; //Hash Key
		public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@DynamoDBAttribute(attributeName = "deviceName")
    String deviceName;
	@DynamoDBAttribute(attributeName = "deviceSeller")
    String deviceSeller;
	@DynamoDBAttribute(attributeName = "ratings")
	String ratings;
	@DynamoDBAttribute(attributeName = "price")
	 String price;
	@DynamoDBAttribute(attributeName = "colour")
    String colour;
	@DynamoDBAttribute(attributeName = "description")
   String description;
	@DynamoDBAttribute(attributeName = "deliveryType")
	String deliveryType;
	@DynamoDBAttribute(attributeName = "looking")
	String looking;
	@DynamoDBAttribute(attributeName = "deviceFeatures")
	String deviceFeatures;
	@DynamoDBAttribute(attributeName = "ratingCount")
	String ratingCount;
	@DynamoDBAttribute(attributeName = "dNumber")
	String dNumber;
	@DynamoDBAttribute(attributeName = "imageUrl")
	String imageUrl;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceSeller() {
		return deviceSeller;
	}
	public void setDeviceSeller(String deviceSeller) {
		this.deviceSeller = deviceSeller;
	}
	public String getRatings() {
		return ratings;
	}
	public void setRatings(String ratings) {
		this.ratings = ratings;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getLooking() {
		return looking;
	}
	public void setLooking(String looking) {
		this.looking = looking;
	}
	public String getDeviceFeatures() {
		return deviceFeatures;
	}
	public void setDeviceFeatures(String deviceFeatures) {
		this.deviceFeatures = deviceFeatures;
	}
	public String getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(String ratingCount) {
		this.ratingCount = ratingCount;
	}
	public String getdNumber() {
		return dNumber;
	}
	public void setdNumber(String dNumber) {
		this.dNumber = dNumber;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}

