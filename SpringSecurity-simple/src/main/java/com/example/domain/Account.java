package com.example.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Account {

	@NotEmpty
	@Size(min=3)
	private String id;
	
	@NotEmpty
	@Size(min=3, max=10)
	private String name;
	
	@NotEmpty
	@Size(min=8,max=65)
	private String password;
	
	private boolean admin;
	
	private boolean enabled;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
