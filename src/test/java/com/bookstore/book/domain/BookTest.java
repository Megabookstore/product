package com.bookstore.book.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookTest {

    @DisplayName("도서를 업데이트한다.")
    @Test
    void update() {
        Book book = Book.builder()
            .bookNumber(new BookNumber("979-11-62245-26-2"))
            .title(new Title("헤드 퍼스트 디자인 패턴"))
            .description(new Description("유지관리가 편리한 객체지향 소프트웨어 만들기!"))
            .contents(new Contents("00장 들어가며 | 이 책을 읽는 방법"))
            .publisher(new Publisher("한빛미디어"))
            .build();

        book.update(
            new BookNumber("979-11-95444-95-3"),
            new Title("GoF의 디자인 패턴 :재사용성을 지닌 객체지향 소프트웨어의 핵심요소"),
            new Description("이 책은 디자인 패턴을 다룬 이론서입니다. 디자인 패턴의 기초적이고 전반적인 내용을 학습할 수 있습니다."),
            new Contents("Chapter1 서론"),
            new Publisher("프로텍미디어")
        );

        assertAll(
            () -> assertThat(book.getBookNumber()).isEqualTo(new BookNumber("979-11-95444-95-3")),
            () -> assertThat(book.getTitle()).isEqualTo(new Title("GoF의 디자인 패턴 :재사용성을 지닌 객체지향 소프트웨어의 핵심요소")),
            () -> assertThat(book.getDescription()).isEqualTo(new Description("이 책은 디자인 패턴을 다룬 이론서입니다. 디자인 패턴의 기초적이고 전반적인 내용을 학습할 수 있습니다.")),
            () -> assertThat(book.getContents()).isEqualTo(new Contents("Chapter1 서론")),
            () -> assertThat(book.getPublisher()).isEqualTo(new Publisher("프로텍미디어"))
        );
    }
}