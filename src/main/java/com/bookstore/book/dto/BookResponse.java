package com.bookstore.book.dto;

import com.bookstore.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookResponse {

    private Long id;

    private String bookNumber;

    private String title;

    private String description;

    private String contents;

    private String publisher;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.bookNumber = book.getBookNumber().getBookNumber();
        this.title = book.getTitle().getTitle();
        this.description = book.getDescription().getDescription();
        this.contents = book.getContents().getContents();
        this.publisher = book.getPublisher().getPublisher();
    }
}
