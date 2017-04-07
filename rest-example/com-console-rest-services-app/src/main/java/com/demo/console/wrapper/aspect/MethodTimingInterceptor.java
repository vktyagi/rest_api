package com.demo.console.wrapper.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

public class MethodTimingInterceptor implements MethodInterceptor {

  private static final Logger LOGGER = Logger.getLogger(MethodTimingInterceptor.class);

  @Override
  public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
    final String methodName = getMethodInvokationName(methodInvocation);
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(new StringBuilder("Method Invocation Begin [").append(methodName).append("]")
          .toString());
    }

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object methodResult = null;
    try {
      methodResult = methodInvocation.proceed();
    } finally {
      stopWatch.stop();

      if (LOGGER.isInfoEnabled()) {
        final long millis = stopWatch.getTime();
        LOGGER.info(new StringBuilder("Method Invocation Complete [").append(methodName)
            .append("] Total Time: ").append(millis).append("(millis)").toString());
      }
    }

    return methodResult;
  }

  /**
   * @param methodInvocation
   * @return
   */
  private String getMethodInvokationName(final MethodInvocation methodInvocation) {
    return new StringBuilder(methodInvocation.getThis().getClass().getName()).append(".")
        .append(methodInvocation.getMethod().getName()).toString();
  }
}