package com.bookstore.book.application;

import com.bookstore.book.domain.Book;
import com.bookstore.book.domain.BookNumber;
import com.bookstore.book.domain.Contents;
import com.bookstore.book.domain.Description;
import com.bookstore.book.domain.Publisher;
import com.bookstore.book.domain.Title;
import com.bookstore.book.dto.BookRequest;
import com.bookstore.book.dto.BookResponse;
import com.bookstore.book.repository.BookRepository;
import com.bookstore.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponse register(BookRequest bookRequest) {
        Book book = bookRepository.save(bookRequest.toBook());

        return new BookResponse(book);
    }

    public BookResponse detail(Long id) {
        Book book = bookFindById(id);

        return new BookResponse(book);
    }

    @Transactional
    public BookResponse update(Long id, BookRequest bookRequest) {
        Book book = bookFindById(id);

        book.update(
            new BookNumber(bookRequest.getBookNumber()),
            new Title(bookRequest.getTitle()),
            new Description(bookRequest.getDescription()),
            new Contents(bookRequest.getContents()),
            new Publisher(bookRequest.getPublisher())
        );

        return new BookResponse(book);
    }

    @Transactional
    public BookResponse delete(Long id) {
        Book book = bookFindById(id);
        bookRepository.delete(book);

        return new BookResponse(book);
    }

    private Book bookFindById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(NotFoundException::new);
    }
}
