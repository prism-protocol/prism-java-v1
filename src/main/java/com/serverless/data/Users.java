package com.serverless.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Users")
public class Users {
	 @DynamoDBHashKey(attributeName = "userId")
	 String userId ;	 
	 @DynamoDBAttribute(attributeName = "doorNumber")
	 String doorNumber;
	 @DynamoDBAttribute(attributeName = "area")
	 String area;
	 @DynamoDBAttribute(attributeName = "landmark")
	 String landmark;
	 @DynamoDBAttribute(attributeName = "pinCode")
	 String pinCode;
	 @DynamoDBAttribute(attributeName = "district")
	 String district;
	 @DynamoDBAttribute(attributeName = "state")
	 String state;
	 @DynamoDBAttribute(attributeName = "country")
	 String country;
	 @DynamoDBAttribute(attributeName = "dob")
	 String dob;
	 @DynamoDBAttribute(attributeName = "dpUrl")
	 String dpUrl;
	 @DynamoDBAttribute(attributeName = "email")
	  String email ;
	 @DynamoDBAttribute(attributeName = "firstName")
	  String firstName ;
	  @DynamoDBAttribute(attributeName = "gender")
	  String gender;
	  @DynamoDBAttribute(attributeName = "height")
	   String height;
	  @DynamoDBAttribute(attributeName = "lastName")
	  String lastName ;
	  @DynamoDBAttribute(attributeName = "phone")
	  String phone;
	  @DynamoDBAttribute(attributeName = "weight")
	  String weight;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDpUrl() {
		return dpUrl;
	}
	public void setDpUrl(String dpUrl) {
		this.dpUrl = dpUrl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	} 
	  

}
