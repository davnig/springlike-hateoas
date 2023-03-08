package com.davnig.springlikehateoas.utils;

import com.davnig.springlikehateoas.core.DefaultMethodInvocationRecording;
import com.davnig.springlikehateoas.core.MethodInvocationRecording;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

public class DummyInvocationUtils {

    public static class InvocationRecorderMethodInterceptor implements MethodInterceptor {

        private final Class<?> type;
        private MethodInvocationRecording invocationRecording;

        public InvocationRecorderMethodInterceptor(Class<?> type) {
            this.type = type;
        }

        @Override
        public Object invoke(MethodInvocation invocation) {
            Method method = invocation.getMethod();
            invocationRecording = new DefaultMethodInvocationRecording(type, method, invocation.getArguments());
            Class<?> returnType = method.getReturnType();
            return returnType.cast(getProxyWithInterceptor(returnType, this));
        }

        public MethodInvocationRecording getInvocationRecording() {
            return invocationRecording;
        }

    }

    /**
     * Create a proxy of the specified {@code type} pre-configured with an interceptor
     * to capture and record method invocations
     *
     * @param type
     * @return a proxy
     */
    public static <T> T methodOn(Class<T> type) {
        Assert.notNull(type, "the given type must not be null");
        return getProxyWithInterceptor(type, new InvocationRecorderMethodInterceptor(type));
    }

    public static MethodInvocationRecording getRecordingFromDummyInvocation(Object dummyInvocation) {
        InvocationRecorderMethodInterceptor invocationRecorderMethodInterceptor =
                (InvocationRecorderMethodInterceptor) ((Advised) dummyInvocation).getAdvisors()[0].getAdvice();
        return invocationRecorderMethodInterceptor.getInvocationRecording();
    }

    @SuppressWarnings("unchecked")
    private static <T> T getProxyWithInterceptor(Class<?> type, InvocationRecorderMethodInterceptor interceptor) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetClass(type);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(interceptor);
        return (T) proxyFactory.getProxy();
    }

}
