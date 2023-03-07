package com.davnig.springlikehateoas.controller;


import com.davnig.springlikehateoas.model.Book;
import com.davnig.springlikehateoas.model.core.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.davnig.springlikehateoas.utils.DummyInvocationUtils.methodOn;


@RestController
@RequestMapping("/greetings")
public class BookController {

    @GetMapping
    public ResponseEntity<Book> greeting() {
        Book book = new Book(1, "Harry Potter")
                .add(Link.of("http://localhost:8080/greetings/1"))
                .add(Link.to(methodOn(BookController.class)));
        return ResponseEntity.ok(book);
    }

}
