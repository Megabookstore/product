package com.bookstore.product.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Price {

    private static final int ZERO = 0;

    private static final String PRICE_LESS_THAN_ZERO = "가격은 0원보다 작을 수 없습니다.";

    @Column(nullable = false)
    private Long price;

    public Price(Long price) {
        validate(price);
        this.price = price;
    }

    private void validate(Long price) {
        validatePriceLessThanZero(price);
    }

    private void validatePriceLessThanZero(Long price) {
        if (price < ZERO) {
            throw new IllegalArgumentException(PRICE_LESS_THAN_ZERO);
        }
    }
}