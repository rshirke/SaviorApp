package com.savior;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="USERFB")
public class UserFB {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="EMAIL")
	private String email;

	@Column(name="FACEBOOKID")
	private String facebookid;
	
	public UserFB() {
	}

	public UserFB(String userName, String email, String facebookId)
	{
		this.username = userName;
		this.email = email;
		this.facebookid = facebookId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFacebookId() {
		return this.facebookid;
	}

	public void setFacebookId(String facebookId) {
		this.facebookid = facebookId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, username='%s', email= %s, FB id = %s]", this.id, this.username, this.email, this.facebookid);
	}
}

