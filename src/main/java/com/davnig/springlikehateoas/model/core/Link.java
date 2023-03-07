package com.davnig.springlikehateoas.model.core;

import lombok.Getter;
import org.springframework.util.Assert;

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
        return new Link("", "");
    }


}
