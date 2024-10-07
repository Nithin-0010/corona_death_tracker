package com.corona.tracker.Model;

public class LocationData {

	private String state;
	private String country;
	private int latestDeathcount;
	private int differFromPY;
	
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
	public int getLatestDeathcount() {
		return latestDeathcount;
	}
	public void setLatestDeathcount(int latestDeathcount) {
		this.latestDeathcount = latestDeathcount;
	}
	public int getDifferFromPY() {
		return differFromPY;
	}
	public void setDifferFromPY(int differFromPY) {
		this.differFromPY = differFromPY;
	}
}
