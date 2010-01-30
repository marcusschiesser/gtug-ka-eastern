package org.gtug.karlsruhe.bunnycacher.common;

import java.io.Serializable;

public class User implements Serializable {

	private String id;
	
	public static User currentUser=new User();

    public User() {
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
