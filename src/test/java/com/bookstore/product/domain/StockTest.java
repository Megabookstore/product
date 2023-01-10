package com.bookstore.product.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class StockTest {

    @DisplayName("재고의 수량이 0보다 작으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = { -1, -1000, -10000 })
    void validateStockLessThanZero(Long stock) {
        assertThatThrownBy(() -> new Stock(stock))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("재고가 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullSource
    void validateStockNotEmpty(Long stock) {
        assertThatThrownBy(() -> new Stock(stock))
            .isInstanceOf(IllegalArgumentException.class);
    }
}