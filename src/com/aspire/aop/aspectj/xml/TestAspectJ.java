package com.aspire.aop.aspectj.xml;

import org.springframework.dao.PessimisticLockingFailureException;

public  class TestAspectJ {	
	public static int COUNT_TOTHROW = 1;
	//spring �����ļ��������Դ����������˴����������ļ������������������Զ����ʧ��
	public static int max_count_defined_in_spring = 3;
	/**
	 * ��ԇ�C�ƣ�COUNT_TOTHROW���������κ�͵�������ɹ������ɿ�aop-around.xml���ò鿴���ԵĴ�����Ϊ3.������֮ǰ������ģ��ʧ�ܻ���
	 * @throws PessimisticLockingFailureException
	 */
	public void haha() throws PessimisticLockingFailureException{
		COUNT_TOTHROW++;
		
		if(COUNT_TOTHROW<=max_count_defined_in_spring){
			System.out.println("TestAspectJ====>����ʧ�ܣ��� throw  Exception������");
			throw new PessimisticLockingFailureException("");
		}
		System.out.println("TestAspectJ====>haha ����ȷ�{�ã����� �����ɹ������������� end");
	}
}
