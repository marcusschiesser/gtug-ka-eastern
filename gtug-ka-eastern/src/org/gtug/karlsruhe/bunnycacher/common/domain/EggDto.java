package org.gtug.karlsruhe.bunnycacher.common.domain;

import java.io.Serializable;
import java.util.Date;

public class EggDto implements Serializable {

	private double latitude;
	private double longitude;
	private Date created;
	private String creator;
	private String hint;
	private long eid;

	public EggDto() {
		
	}
	
	public EggDto(long eid, double latitude, double longitude, String hint) {
		this();
		this.latitude = latitude;
		this.longitude = longitude;
		this.hint = hint;
		this.eid = eid;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public long getEid() {
		return eid;
	}

}
