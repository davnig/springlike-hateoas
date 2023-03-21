package com.davnig.springlikehateoas.controller;


import com.davnig.springlikehateoas.core.Link;
import com.davnig.springlikehateoas.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.davnig.springlikehateoas.utils.DummyInvocationUtils.methodOn;


@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/1")
    public ResponseEntity<Book> demo() {
        Book book = new Book(1, "Harry Potter")
                .add(Link.of("http://localhost:8080/books/1"))
                .add(Link.to(methodOn(BookController.class).demo()));
        return ResponseEntity.ok(book);
    }

}
