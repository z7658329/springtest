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

public class TestATest {
	@Test
	public void test1(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-test.xml");
		spring.annotation.TestAnnotation test = (spring.annotation.TestAnnotation) applicationContext.getBean("auto_testa");
		System.out.println(test);
	}
	@Test
	public void testShilihua(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/test_shilihua.xml");
		 
	}
	@Test
	public void testStaticFactory(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-test.xml");
		StaticFactory test = (StaticFactory) applicationContext.getBean("exampleBean");
		System.out.println(test);
	}
	
	@Test
	public void testStaticInstanceFactory(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-test.xml");
		TestA test = (TestA) applicationContext.getBean("testa_factory");
		System.out.println(test);
		System.out.println(test.getClass().getName());
	}
	
	@Test
	public void test2(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-test.xml");
		A test = (A) applicationContext.getBean("a");
		A test2 = (A) applicationContext.getBean("a");
		System.out.println(test==test2);
		System.out.println(test.createB()==test2.createB());
		System.out.println(test);
	}
	
	@Test
	public void testscope(){
		//改为ClassPathXmlApplicationContext 才可以调用destory方法！！！
//		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/test_scope.xml");
//		SingleType sin1 = (SingleType) applicationContext.getBean("single");
//		SingleType sin2 = (SingleType) applicationContext.getBean("single");
//		System.out.println(sin1==sin2);
//		
//		ProtoType pro1 = (ProtoType) applicationContext.getBean("prototype");
//		ProtoType pro2 = (ProtoType) applicationContext.getBean("prototype");
//		System.out.println(pro1==pro2);
//		applicationContext.destroy();
		
		//或者
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/test_scope.xml");
		SingleType sin1 = (SingleType) applicationContext.getBean("single");
		SingleType sin2 = (SingleType) applicationContext.getBean("single");
		System.out.println(sin1==sin2);
		
		ProtoType pro1 = (ProtoType) applicationContext.getBean("prototype");
		ProtoType pro2 = (ProtoType) applicationContext.getBean("prototype");
		System.out.println(pro1==pro2);
		applicationContext.registerShutdownHook();
	}
	
	@Test
	public void testsDefaultMtthods(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/beans-default-methods.xml");
		System.out.println("============容器初始化完毕================================");
		//如果这里不调用该bean的话，则不会初始化该bean  ProtoType
		com.defaul.methods.SinType sin2 = (com.defaul.methods.SinType) applicationContext.getBean("sintype");		
	}
	
	@Test
	public void testsBeanFactoryAware(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/beans-default-methods.xml");
		System.out.println("----------------------------");
		BeanAwareTest applicationContext2 = (BeanAwareTest)applicationContext.getBean("beanAware");
		System.out.println("=======================");
		com.defaul.methods.SinType sin2 = (com.defaul.methods.SinType) applicationContext2.getAppliactionContext().getBean("sintype");		
	}
	
	@Test
	public void testsMessageSource(){
		MessageSource messageSource = new ClassPathXmlApplicationContext("classpath:spring/messageSource.xml");
		System.out.println("----------------------------");
		String message =messageSource.getMessage("argument.required", new Object[]{"userDao"}, "Required", null);
		System.out.println(message);
	}
	
	@Test
	public void testAnnotation(){
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/test_annotation.xml");
		TestAnnotation sin1 = (TestAnnotation) applicationContext.getBean("anno");
		System.out.println(sin1);
		applicationContext.registerShutdownHook();
	}
	
	@Test
	public void testsResourceLoaderAware(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/testResourceLoaderAwaer.xml");
		System.out.println("----------------------------");
		ResourceLoaderUtil applicationContext2 = (ResourceLoaderUtil)applicationContext.getBean("resLoader");
		Resource rs = applicationContext2.getResource().getResource("classpath:format.properties");
		try {
			System.out.println(rs.getURL().getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testsApplicationContextAware(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/testResourceLoaderAwaer.xml");
		System.out.println("----------------------------");
		ApplicationContextAwareUtil applicationContext2 = (ApplicationContextAwareUtil)applicationContext.getBean("applicationLoader");
		Resource rs = applicationContext2.getApplicationContext().getResource("classpath:format.properties");
		try {
			System.out.println(rs.getURL().getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取message！！
		String message = applicationContext2.getApplicationContext().getMessage("argument.required", new Object[]{"userDao"}, "Required", null);
		System.out.println("message:"+message);
	}
	
	@Test
	/**
	 * 把resource 当做属性注入
	 */
	public void testsResource(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/testResourceLoaderAwaer.xml");
		System.out.println("-----------容器初始化完毕-----------------");
		ResourceTest applicationContext2 = (ResourceTest)applicationContext.getBean("resourcetest");
		Resource rs = applicationContext2.getResource();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(rs.getInputStream()));
			System.out.println("读取内容为:"+br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
