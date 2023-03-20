package com.davnig.springlikehateoas.utils;

import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;

import static org.springframework.core.annotation.AnnotationUtils.getValue;

public class AnnotationUtils {

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
