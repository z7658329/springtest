package spring.autowire;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scope.ProtoType;
import com.scope.SingleType;

public class BeanAwareTest implements BeanFactoryAware{
	public BeanFactory appliactionContext ;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		appliactionContext = beanFactory;
		System.out.println("BeanFactory==>:"+appliactionContext);
	}
	public BeanFactory getAppliactionContext() {
		return appliactionContext;
	}
	public void setAppliactionContext(BeanFactory appliactionContext) {
		this.appliactionContext = appliactionContext;
	}
	
}
