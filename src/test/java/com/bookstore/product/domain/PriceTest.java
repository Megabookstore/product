package com.bookstore.product.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PriceTest {

    @DisplayName("가격은 0원보다 작으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = { -1, -1000, -10000 })
    void validatePriceLessThanZero(Long price) {
        assertThatThrownBy(() -> new Price(price))
            .isInstanceOf(IllegalArgumentException.class);
    }
}