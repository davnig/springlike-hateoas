package com.davnig.springlikehateoas.core;

import java.lang.reflect.Method;

public interface MethodInvocationRecording {

    Class<?> getTargetType();

    Method getTargetMethod();

    Object[] getInvocationArguments();

}
