package org.gtug.karlsruhe.bunnycasher.client.common;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
