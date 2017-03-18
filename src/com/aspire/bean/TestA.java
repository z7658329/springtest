package com.aspire.bean;

public class TestA {
	private String username;
	
	public void init() {
		System.out.println("A is chu初始化方法niit!!");
	}
	public TestA() {
		super();
		System.out.println("testa is init ");
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "TestA [ username=" + username + "]";
	}
	
}
