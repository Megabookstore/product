package com.bookstore.book.domain;

import com.bookstore.common.domain.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private BookNumber bookNumber;

    @Embedded
    private Title title;

    @Embedded
    private Description description;

    @Embedded
    private Contents contents;

    @Embedded
    private Publisher publisher;

    @Column
    private Long productId;

    public void update(BookNumber bookNumber, Title title, Description description,
        Contents contents, Publisher publisher) {
        this.bookNumber = bookNumber;
        this.title = title;
        this.description = description;
        this.contents = contents;
        this.publisher = publisher;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookNumber,
            book.bookNumber) && Objects.equals(title, book.title) && Objects.equals(
            description, book.description) && Objects.equals(contents, book.contents)
            && Objects.equals(publisher, book.publisher) && Objects.equals(
            productId, book.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookNumber, title, description, contents, publisher, productId);
    }
}
