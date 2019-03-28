package com.serverless.data;

public class TransactionWallet {
	private String userId;
	private String emailid;
	private String title;
	private String pointsFor;
	private String timeStamp;
	private float points;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPointsFor() {
		return pointsFor;
	}
	public void setPointsFor(String pointsFor) {
		this.pointsFor = pointsFor;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}

}
