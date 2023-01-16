package com.bookstore.book.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class BookNumberTest {

    @DisplayName("국제표준도서번호(ISBN)은 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateBookNumberNotEmpty(String bookNumber) {
        assertThatThrownBy(() -> new BookNumber(bookNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("국제표준도서번호(ISBN)은 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "A78-99-6570-060-A", "978-99-A5B0-060-9", "978-CD-6570-A60-9" })
    void validateBookNumberPattern(String bookNumber) {
        assertThatThrownBy(() -> new BookNumber(bookNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }
}