package com.davnig.springlikehateoas.core;

import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

@AllArgsConstructor
public class DefaultMethodInvocationRecording implements MethodInvocationRecording {

    private Class<?> targetType;
    private Method targetMethod;
    private Object[] invocationArguments;

    @Override
    public Class<?> getTargetType() {
        return targetType;
    }

    @Override
    public Method getTargetMethod() {
        return targetMethod;
    }

    @Override
    public Object[] getInvocationArguments() {
        return invocationArguments;
    }

}
