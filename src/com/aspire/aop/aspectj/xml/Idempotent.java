package com.aspire.aop.aspectj.xml;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
  // marker annotation
}
