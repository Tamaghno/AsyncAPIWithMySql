package com.tamcodes.reactiveprojectwithdb.repositories;


import com.tamcodes.reactiveprojectwithdb.entities.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.*;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book,Integer> {

    Mono<Book> findByName(String name); //finderMethod

    Flux<Book> findByAuthor(String author); //finderMethod

    Flux<Book> findByPublisher(String publisher); //finderMethod

    Flux<Book> findByNameAndAuthor(String name, String author); //finderMethod

    @Query("select * from book_details where author = :author")
    Flux<Book> getAllBooksByAuthor(String author);

    @Query("select * from book_details where name like :word")
    Flux<Book> getAllBooksByWord(String word);

}
