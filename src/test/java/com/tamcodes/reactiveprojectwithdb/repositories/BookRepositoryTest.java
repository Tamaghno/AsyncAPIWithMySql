package com.tamcodes.reactiveprojectwithdb.repositories;

import com.tamcodes.reactiveprojectwithdb.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findMethodTest() throws InterruptedException {

        bookRepository.findByName("bookname").log().subscribe(data -> {
            System.out.println(data.getAuthor());
            System.out.println("Mono thread name : "+Thread.currentThread().getName());
        });

        System.out.println("Main thread name : "+Thread.currentThread().getName());
        Thread.sleep(3000);
    }

    @Test
    public void queryMethodTestforAuthor() throws InterruptedException {

        String author = "Tam";

        Flux<Book> bookFlux = bookRepository.getAllBooksByAuthor(author);

        bookFlux.log().subscribe(data -> {
            System.out.println("Book name : "+data .getName());
        });

        Thread.sleep(3000);

        StepVerifier.create(bookFlux).expectNextCount(1).verifyComplete();

    }

    @Test
    public void queryMethodTestforWord() throws InterruptedException {

        String word = "%book%";

        Flux<Book> bookFlux = bookRepository.getAllBooksByWord(word);

        bookFlux.log().subscribe(data -> {
            System.out.println("Book name : "+data .getName());
        });

        Thread.sleep(3000);

        StepVerifier.create(bookFlux).expectNextCount(2).verifyComplete();

    }
}