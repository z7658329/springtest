package com.aspire.bean;

public class B {
	
	
	public B() {
		super();
		System.out.println("B ³õÊ¼»¯");
	}

	private String username="b";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "B [username=" + username + "]";
	}
	
}
