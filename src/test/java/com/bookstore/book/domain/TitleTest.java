package com.bookstore.book.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class TitleTest {

    @DisplayName("제목은 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateTitleNotEmpty(String title) {
        assertThatThrownBy(() -> new Title(title))
            .isInstanceOf(IllegalArgumentException.class);
    }
}