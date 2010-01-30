package org.gtug.karlsruhe.bunnycacher.common;

import java.io.Serializable;
import java.util.Date;

public class Tag implements Serializable {

	private String message;
	private Date timestamp;
	private User user;
	private Egg egg;

    public Tag() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Egg getEgg() {
		return egg;
	}

	public void setEgg(Egg egg) {
		this.egg = egg;
	}

}
