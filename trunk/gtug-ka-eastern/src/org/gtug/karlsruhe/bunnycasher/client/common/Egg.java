package org.gtug.karlsruhe.bunnycasher.client.common;

import java.util.Date;

public class Egg {
	
	private double latitude;
	private double longitude;
	private String hint;
	private Date created;
	private User creator;

	public Egg(double latitude, double longitude, String hint) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.hint = hint;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

}