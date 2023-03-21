package com.davnig.springlikehateoas.model;

import com.davnig.springlikehateoas.core.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book extends RepresentationModel<Book> {

    private int id;
    private String title;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

}
