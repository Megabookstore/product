package com.bookstore.book.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PublisherTest {

    @DisplayName("출판사는 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateTitleNotEmpty(String publisher) {
        assertThatThrownBy(() -> new Publisher(publisher))
            .isInstanceOf(IllegalArgumentException.class);
    }
}