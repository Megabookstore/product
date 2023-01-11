package com.bookstore.product.domain;

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
@EqualsAndHashCode
public class Price {

    private static final int ZERO = 0;

    private static final String PRICE_LESS_THAN_ZERO = "가격은 0원보다 작을 수 없습니다.";

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
            throw new IllegalArgumentException(PRICE_LESS_THAN_ZERO);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price1 = (Price) o;
        return Objects.equals(price, price1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
