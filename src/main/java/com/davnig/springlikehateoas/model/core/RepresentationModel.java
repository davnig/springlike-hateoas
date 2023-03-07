package com.davnig.springlikehateoas.model.core;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepresentationModel<T extends RepresentationModel<? extends T>> {

    private List<Link> links;

    public RepresentationModel() {
        links = new ArrayList<>();
    }

    public RepresentationModel(Link... links) {
        this.links = new ArrayList<>();
        this.links.addAll(Arrays.asList(links));
    }

    public T add(Link link) {
        Assert.notNull(link, "the given link must not be null");
        this.links.add(link);
        return (T) this;
    }

    public T add(Link... links) {
        Assert.notNull(links, "the given array of links must not be null");
        this.links.addAll(Arrays.asList(links));
        return (T) this;
    }

}
