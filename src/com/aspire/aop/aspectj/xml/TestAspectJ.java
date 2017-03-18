package com.aspire.aop.aspectj.xml;

import org.springframework.dao.PessimisticLockingFailureException;

public  class TestAspectJ {	
	public static int COUNT_TOTHROW = 1;
	//spring 配置文件里的最大尝试次数。。若此处大于配置文件里的最大次数。。则永远都会失败
	public static int max_count_defined_in_spring = 3;
	/**
	 * 重試機制，COUNT_TOTHROW尝试了三次后就当做处理成功！（可看aop-around.xml配置查看尝试的次数！为3.。。）之前几次我模拟失败机制
	 * @throws PessimisticLockingFailureException
	 */
	public void haha() throws PessimisticLockingFailureException{
		COUNT_TOTHROW++;
		
		if(COUNT_TOTHROW<=max_count_defined_in_spring){
			System.out.println("TestAspectJ====>操作失败！！ throw  Exception！！！");
			throw new PessimisticLockingFailureException("");
		}
		System.out.println("TestAspectJ====>haha 被正确調用！！！ 操作成功！！！！！！ end");
	}
}
