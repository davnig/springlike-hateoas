package com.davnig.springlikehateoas.model.core;

import com.davnig.springlikehateoas.core.MethodInvocationRecording;
import com.davnig.springlikehateoas.utils.DummyInvocationUtils;
import lombok.Getter;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

import static com.davnig.springlikehateoas.utils.AnnotationUtils.getMappingFrom;
import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

@Getter
public class Link {

    private String reference;
    private String relation;

    private Link(String reference, String relation) {
        this.reference = reference;
        this.relation = relation;
    }

    public static Link of(String reference) {
        Assert.hasText(reference, "the reference must have some text");
        return new Link(reference, "self");
    }

    public static Link of(String reference, String relation) {
        Assert.hasText(reference, "the reference must have some text");
        Assert.notNull(relation, "the relation must not be null");
        return new Link(reference, relation);
    }

    public static Link to(Object dummyInvocation) {
        MethodInvocationRecording methodInvocationRecording =
                DummyInvocationUtils.getRecordingFromDummyInvocation(dummyInvocation);
        Class<?> targetType = methodInvocationRecording.getTargetType();
        Method targetMethod = methodInvocationRecording.getTargetMethod();
        String methodLevelMapping = getMappingFrom(findMergedAnnotation(targetMethod, RequestMapping.class))[0];
        String controllerLevelMapping = getMappingFrom(findMergedAnnotation(targetType, RequestMapping.class))[0];
        return new Link(controllerLevelMapping + methodLevelMapping, "");
    }




}
