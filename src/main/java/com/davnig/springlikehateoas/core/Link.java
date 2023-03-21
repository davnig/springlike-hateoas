package com.davnig.springlikehateoas.core;

import com.davnig.springlikehateoas.utils.AnnotationUtils;
import com.davnig.springlikehateoas.utils.DummyInvocationUtils;
import lombok.Getter;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Method;

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
        Class<?> controller = methodInvocationRecording.getTargetType();
        Method method = methodInvocationRecording.getTargetMethod();
        String host = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String path = AnnotationUtils.getControllerMethodPath(controller, method);
        return new Link(host + path, "");
    }




}
