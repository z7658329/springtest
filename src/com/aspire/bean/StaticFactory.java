package com.aspire.bean;

public class StaticFactory {
	public static StaticFactory createInstance(){
		return new StaticFactory();
	}
}
