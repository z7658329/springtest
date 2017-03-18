package com.scope;

import org.aspectj.lang.annotation.Before;

public class ProtoType {
	private String username="prototype";

	public ProtoType() {
		super();
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
