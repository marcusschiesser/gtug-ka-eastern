package org.gtug.karlsruhe.bunnycasher.client.common;

public class User {

	private String id;
	
	public static User currentUser=new User();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
