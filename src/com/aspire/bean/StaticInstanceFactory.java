package com.aspire.bean;

public class StaticInstanceFactory {
	
	
	
	public StaticInstanceFactory() {
		super();
		System.out.println("StaticInstanceFactory init");
	}

	public static TestA createInstance2(){
		return new TestA();
	}
}
