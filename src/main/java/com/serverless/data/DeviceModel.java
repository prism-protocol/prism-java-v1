package com.serverless.data;

import java.util.List;

public class DeviceModel {

	private String message;
	private int response;
	private List<Devices> deviceData;
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
	public List<Devices> getDeviceData() {
		return deviceData;
	}
	public void setDeviceData(List<Devices> deviceData) {
		this.deviceData = deviceData;
	}
}
