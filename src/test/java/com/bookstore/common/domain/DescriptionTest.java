package com.bookstore.common.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.bookstore.book.domain.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class DescriptionTest {

    @DisplayName("설명이 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateNameNotEmpty(String description) {
        assertThatThrownBy(() -> new Description(description))
            .isInstanceOf(IllegalArgumentException.class);
    }
}