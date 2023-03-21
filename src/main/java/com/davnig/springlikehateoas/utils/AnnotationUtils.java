package com.davnig.springlikehateoas.utils;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;
import static org.springframework.core.annotation.AnnotationUtils.getValue;

public class AnnotationUtils {


    public static String getControllerMethodPath(Class<?> controller, Method method) {
        String methodLevelMapping = getMappingFrom(findMergedAnnotation(method, RequestMapping.class))[0];
        String controllerLevelMapping = getMappingFrom(findMergedAnnotation(controller, RequestMapping.class))[0];
        return controllerLevelMapping + methodLevelMapping;
    }


    public static String[] getMappingFrom(@Nullable Annotation annotation) {
        if (annotation == null) {
            return new String[0];
        }
        Object value = getValue(annotation);
        if (value instanceof String) {
            return new String[]{(String) value};
        } else if (value instanceof String[]) {
            return (String[]) value;
        } else if (value == null) {
            return new String[0];
        }
        throw new IllegalStateException(String.format(
                "Unsupported type for the mapping attribute! Support String and String[] but got %s!", value.getClass()));
    }

}
