package com.aspire.aop.aspectj.xml;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;

//����xml����ʵ��
public class ConcurrentOperationExecutor implements Ordered {
   
   private static final int DEFAULT_MAX_RETRIES = 2;

   private int maxRetries = DEFAULT_MAX_RETRIES;
   private int order = 1;

   public void setMaxRetries(int maxRetries) {
      this.maxRetries = maxRetries;
   }
   
   public int getOrder() {
      return this.order;
   }
   
   public void setOrder(int order) {
      this.order = order;
   }
   
//   ����xml����ʵ��
   public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable { 
      int numAttempts = 0;
      PessimisticLockingFailureException lockFailureException;
      do {
         numAttempts++;
         try { 
        	System.out.println("numAttempts is:"+numAttempts);
            return pjp.proceed();
         }
         catch(PessimisticLockingFailureException ex) {
            lockFailureException = ex;
         }
      }
      while(numAttempts < this.maxRetries);
      
      //����maxRetries�κ���ʧ�ܣ�������
      System.out.println("======����"+this.maxRetries+"�κ���ʧ�ܣ����Խ��������쳣=============");
      throw lockFailureException;
   }

}
