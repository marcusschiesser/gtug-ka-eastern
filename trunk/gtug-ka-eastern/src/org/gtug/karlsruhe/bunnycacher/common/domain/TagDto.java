package org.gtug.karlsruhe.bunnycacher.common.domain;

import java.io.Serializable;
import java.util.Date;

public class TagDto implements Serializable {

	private String message;
	private Date timestamp;
	private UserDto user;
	private EggDto egg;

	public TagDto() {

	}

	public TagDto(String message, Date timestamp, UserDto user, EggDto egg) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.user = user;
		this.egg = egg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public EggDto getEgg() {
		return egg;
	}

	public void setEgg(EggDto egg) {
		this.egg = egg;
	}

}
