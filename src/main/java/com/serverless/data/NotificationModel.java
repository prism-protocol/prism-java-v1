package com.serverless.data;

import java.util.List;

public class NotificationModel {
	
	private String message;
	private int response;
	private List<UserNotifications> notificationList;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getResponse() {
		return response;
	}
	public void setResponse(int response) {
		this.response = response;
	}
	public List<UserNotifications> getNotificationList() {
		return notificationList;
	}
	public void setNotificationList(List<UserNotifications> notificationList) {
		this.notificationList = notificationList;
	}

}
