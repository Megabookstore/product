package com.bookstore.product.domain;

import com.bookstore.common.constant.ErrorCode;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Price {

    private static final int ZERO = 0;


    @Column(nullable = false)
    private BigDecimal price;

    public Price(Long price) {
        validate(price);
        this.price = BigDecimal.valueOf(price);
    }

    private void validate(Long price) {
        validatePriceLessThanZero(price);
    }

    private void validatePriceLessThanZero(Long price) {
        if (price < ZERO) {
            throw new IllegalArgumentException(ErrorCode.PRICE_LESS_THAN_ZERO.getErrorMessage());
        }
    }
}
