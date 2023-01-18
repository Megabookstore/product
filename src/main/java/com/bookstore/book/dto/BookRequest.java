package com.bookstore.book.dto;

import com.bookstore.book.domain.Book;
import com.bookstore.book.domain.BookNumber;
import com.bookstore.book.domain.Contents;
import com.bookstore.book.domain.Description;
import com.bookstore.book.domain.Publisher;
import com.bookstore.book.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookRequest {

    private String bookNumber;

    private String title;

    private String description;

    private String contents;

    private String publisher;

    public Book toBook() {
        return Book.builder()
            .bookNumber(new BookNumber(bookNumber))
            .title(new Title(title))
            .description(new Description(description))
            .contents(new Contents(contents))
            .publisher(new Publisher(publisher))
            .build();
    }
}
