package org.gtug.karlsruhe.bunnycacher.common.domain;

import java.io.Serializable;

public class UserDto implements Serializable {

	private String googleId;
	
	public UserDto() {
		
	}

	public UserDto(String googleId) {
		super();
		this.googleId = googleId;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	
}
