package org.infsys.pharmacy.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3981915115134732566L;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private UserType type;
	private boolean logicallyDeleted;
	
	public User() {
	}
	
	public User(String username, String password, String firstName, String lastName, UserType type) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public boolean isLogicallyDeleted() {
		return logicallyDeleted;
	}

	public void setLogicallyDeleted(boolean logicallyDeleted) {
		this.logicallyDeleted = logicallyDeleted;
	}
}