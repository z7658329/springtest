package com.defaul.methods;

public class SingleType {
	private String username="SingleType";

	public void init(){
		System.out.println("SingleType init");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "SingleType [username=" + username + "]";
	}
	
	public void destory(){
		System.out.println("SingleType destory");
	}
}
