package org.gtug.karlsruhe.bunnycacher.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String googleId;
	
	public static User currentUser=new User();

    public User() {
    }

	public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
		this.id = id;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

}
