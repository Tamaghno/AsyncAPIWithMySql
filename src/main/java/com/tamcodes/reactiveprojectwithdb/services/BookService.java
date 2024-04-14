package com.tamcodes.reactiveprojectwithdb.services;

import com.tamcodes.reactiveprojectwithdb.entities.Book;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface BookService {

    public Mono<Book> create(Book book);

    public Flux<Book> getAll();

    public Mono<Book> get(int bookId);

    public Mono<Book> update(Book book, int bookId);

    public void delete(int bookId);

    public Flux<Book> search(String qry);

    Flux<Book> searchBooks(String titleKeyword);


}
