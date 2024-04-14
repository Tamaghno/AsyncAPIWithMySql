package com.tamcodes.reactiveprojectwithdb.servicesImpl;

import com.tamcodes.reactiveprojectwithdb.entities.Book;
import com.tamcodes.reactiveprojectwithdb.repositories.BookRepository;
import com.tamcodes.reactiveprojectwithdb.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Mono<Book> create(Book book) {
       return bookRepository.save(book);
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> get(int bookId) {
        return bookRepository.findById(Mono.just(bookId));
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        Mono<Book> oldBook = bookRepository.findById(bookId);
        oldBook.flatMap(book1 -> {
            book1.setAuthor(book.getAuthor());
            book1.setDescription(book.getDescription());
            book1.setName(book.getName());
            book1.setPublisher(book.getPublisher());
            return bookRepository.save(book1);
        });
        return oldBook;
    }

    @Override
    public void delete(int bookId) {
        bookRepository.findById(bookId).flatMap(book -> bookRepository.delete(book));
    }

    @Override
    public Flux<Book> search(String qry) {
        return null;
    }

    @Override
    public Flux<Book> searchBooks(String titleKeyword) {

        return bookRepository.getAllBooksByWord("%"+titleKeyword+"%");

    }
}
