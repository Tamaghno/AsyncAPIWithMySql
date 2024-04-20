package com.tamcodes.reactiveprojectwithdb.experimentals;

import com.tamcodes.reactiveprojectwithdb.entities.Book;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FlatmapAndMapsTest {

    private Flux<Book> flux() {
        return Flux.just(
                new Book(1, "Book1", "DESC1", "PUB1", "AUTH1"),
                new Book(2, "Bookbook2", "DESC1", "PUB1", "AUTH1"),
                new Book(3, "Book3", "DESC1", "PUB1", "AUTH1"),
                new Book(4, "Bookbook`4", "DESC1", "PUB1", "AUTH1")
        )
                .delayElements(Duration.ofSeconds(1));
    }

    @Test
    void tryMaps() throws InterruptedException {
        System.out.println("Starting test .......");
        Flux<Book> flux = flux();

        flux.map(book -> {
            book.setBookId(-1);
            return book;
        }).subscribe(book -> System.out.println("book :"+book.toString()));

        System.out.println("Ending test .......");


        Thread.sleep(4001);

    }

    @Test
    void tryFlatMaps() throws InterruptedException {
        System.out.println("Starting test .......");
        Flux<Book> flux = flux();

        flux.flatMap(book -> {
            return Flux.just(book,
                    new Book(book.getBookId()*10, book.getName()+"_new", book.getDescription()+"_new", "PUB10", "AUTH10"),
                    new Book(book.getBookId()*100, book.getName()+"_new2", book.getDescription()+"_new2", "PUB100", "AUTH100"))
                    .delayElements(Duration.ofSeconds(3));
        })
                .delayElements(Duration.ofSeconds(3))
                .log("tam.flux.")
                .subscribe(book -> System.out.println("book :"+book.toString()));

        System.out.println("Ending test .......");


        Thread.sleep(100001);

    }

    @Test
    void tryFlatMapsWithFilter() throws InterruptedException {
        System.out.println("Starting test .......");
        Flux<Book> flux = flux();

        flux.flatMap(book -> {
                    return Flux.just(book,
                                    new Book(book.getBookId()*10, book.getName()+"_new", book.getDescription()+"_new", "PUB10", "AUTH10"),
                                    new Book(book.getBookId()*100, book.getName()+"_new2", book.getDescription()+"_new2", "PUB100", "AUTH100"))
                            .delayElements(Duration.ofSeconds(3))
                            .filter(book1 -> book1.getName().length()>10);
                })
                .delayElements(Duration.ofSeconds(3))
//                .log("tam.flux.")
                .subscribe(book -> System.out.println("book :"+book.toString()));

        System.out.println("Ending test .......");


        Thread.sleep(100001);

    }
}
