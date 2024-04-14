package com.tamcodes.reactiveprojectwithdb.controllers;

import com.tamcodes.reactiveprojectwithdb.entities.Book;
import com.tamcodes.reactiveprojectwithdb.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Mono<Book> create(@RequestBody Book book){
        return bookService.create(book);
    }

    @GetMapping
    public Flux<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public Mono<Book> get(@PathVariable int bookId){
        return bookService.get(bookId);
    }

    @PatchMapping("/{bookId}")
    public Mono<Book> update(@RequestBody Book book, @PathVariable int bookId){
        return bookService.update(book, bookId);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable int bookId){
        bookService.delete(bookId);
    }

    @GetMapping("/search/{titleKeyword}")
    public Flux<Book> searchBooks(@RequestParam("titleKeyword") String title){
       return bookService.searchBooks(title);
    }

    //localhost:8081/books/searchquery?query=book
    @GetMapping("/searchquery")
    public Flux<Book> searchBooksQuery(@RequestParam("query") String query){
        return bookService.searchBooks(query);
    }
}
