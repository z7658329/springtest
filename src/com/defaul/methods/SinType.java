package com.defaul.methods;

public class SinType {
	private String username="prototype";

	public void init(){
		System.out.println("ProtoType init");
	}
	@Override
	public String toString() {
		return "ProtoType [username=" + username + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void destory(){
		System.out.println("ProtoType destory");
	}
}
