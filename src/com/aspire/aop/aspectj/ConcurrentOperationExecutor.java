package com.aspire.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;

@Aspect
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
   
   @Around("com.aspire.aop.aspectj.SystemArchitecture.businessService()")
   /**
    * ʹ�ù���ͨ������㶨�� SystemArchitecture ��һ�����������c���
    * ���кö��������c�������˴�businessService�� execution(* com.aspire.aop.aspectj.*.*(..))
    * ����ֱ�ӽ� @Around��valueֵ��Ϊ�� execution(* com.aspire.aop.aspectj.*.*(..))
    * ��һ����˼��ֻ������ execution(* com.aspire.aop.aspectj.*.*(..))������һ������������������
    * @param pjp
    * @return
    * @throws Throwable
    */
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
