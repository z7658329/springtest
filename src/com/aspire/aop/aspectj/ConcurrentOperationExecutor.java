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
    * 使用共享通用切入点定义 SystemArchitecture 是一個共享切入點的類，
    * 他有好多個切入點！！！此处businessService是 execution(* com.aspire.aop.aspectj.*.*(..))
    * 可以直接将 @Around的value值改为： execution(* com.aspire.aop.aspectj.*.*(..))
    * 是一个意思，只不过将 execution(* com.aspire.aop.aspectj.*.*(..))定义在一个共享切入点的类里面
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
      
      //尝试maxRetries次后还是失败！！！！
      System.out.println("======尝试"+this.maxRetries+"次后还是失败，尝试结束！抛异常=============");
      throw lockFailureException;
   }

}
