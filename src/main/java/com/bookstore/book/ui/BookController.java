package com.bookstore.book.ui;

import com.bookstore.book.application.BookService;
import com.bookstore.book.dto.BookRequest;
import com.bookstore.book.dto.BookResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> register(@RequestBody final BookRequest bookRequest) {
        BookResponse bookResponse = bookService.register(bookRequest);
        URI uri = URI.create("/book/" + bookResponse.getId());
        return ResponseEntity.created(uri).body(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> detail(@PathVariable("id") Long id) {
        BookResponse bookResponse = bookService.detail(id);
        return ResponseEntity.ok().body(bookResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
        @RequestBody final BookRequest bookRequest,
        @PathVariable("id") Long id) {
        BookResponse bookResponse = bookService.update(id, bookRequest);
        return ResponseEntity.ok().body(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> delete(@PathVariable("id") Long id) {
        BookResponse bookResponse = bookService.delete(id);
        return ResponseEntity.ok().body(bookResponse);
    }
}
