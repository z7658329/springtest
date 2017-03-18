package com.aspire.aop.aspectj.xml;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;

//采用xml配置实现
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
   
//   采用xml配置实现
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
      
      //尝试maxRetries次后还是失败！！！！
      System.out.println("======尝试"+this.maxRetries+"次后还是失败，尝试结束！抛异常=============");
      throw lockFailureException;
   }

}
