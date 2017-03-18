package com.aspire.bean;

public class TestB {
	private String username="default B name";
	private int age;
	
	
	
	public TestB() {
		super();
		System.out.println("testb is init "+this.username);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
