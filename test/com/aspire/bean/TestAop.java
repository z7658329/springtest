package com.aspire.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import spring.annotation.TestAnnotation;
import spring.autowire.BeanAwareTest;
import spring.resourceloader.ApplicationContextAwareUtil;
import spring.resourceloader.ResourceLoaderUtil;
import spring.resourceloader.ResourceTest;

import com.aspire.aop.Foo;
import com.aspire.aop.FooService;
import com.aspire.aop.aspectj.ConcurrentOperationExecutor;
import com.aspire.aop.aspectj.TestAspectJ;
import com.scope.ProtoType;
import com.scope.SingleType;

public class TestAop {
	@Test
	/**
	 * 測試aop 環繞通知 around
	 */
	public void testsAopAround(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/aop-around.xml");
	    FooService foo = (FooService) ctx.getBean("fooService");
	    Foo f = foo.getFoo("Pengo", 12);
	}
	@Test
	/**
	 * 測試aop AspectJ 基于annotation  @aspectj实现
	 */
	public void testAopAspectJAnnotation() throws Exception{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/aop-aspectj-annotation.xml");
		System.out.println("-----------容器初始化完毕-----------------");
	    TestAspectJ aspectj = (TestAspectJ) ctx.getBean("aspectj");
	    aspectj.haha();
	}
	@Test
	/**
	 * 測試aop AspectJ 基于xml配置实现
	 */
	public void testAopAspectJXML() throws Exception{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/aop-aspectj-xml.xml");
		System.out.println("-----------容器初始化完毕-----------------");
	    TestAspectJ aspectj = (TestAspectJ) ctx.getBean("aspectj");
	    aspectj.haha();
	}
}
