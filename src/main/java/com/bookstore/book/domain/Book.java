package com.bookstore.book.domain;

import com.bookstore.common.domain.BaseEntity;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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
}
