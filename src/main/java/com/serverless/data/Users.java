package com.serverless.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Users")
public class Users {
	 @DynamoDBHashKey(attributeName = "userId")
	 String userId ;
	 @DynamoDBAttribute(attributeName = "address")
	 String address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
