package spring.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestAnnotation {
	@PostConstruct
	public void init() {
		System.out.println("TestAnnotation is chu��ʼ������niit!!");
	}
	@PreDestroy
	public void dispose() {
		System.out.println("TestAnnotation is dispose");
	}
}
