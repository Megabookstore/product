package com.bookstore.product.domain;

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
public class Stock {

    private static final int ZERO = 0;

    private static final String STOCK_LESS_THAN_ZERO = "재고의 수량은 0보다 작을 수 없습니다.";

    private static final String STOCK_NOT_EMPTY = "재고는 빈 값을 허용하지 않습니다.";

    @Column(nullable = false)
    private Long stock;

    public Stock(Long stock) {
        validate(stock);
        this.stock = stock;
    }

    private void validate(Long stock) {
        validateStockNotEmpty(stock);
        validateStockLessThanZero(stock);
    }

    private void validateStockNotEmpty(Long stock) {
        if (Objects.isNull(stock)) {
            throw new IllegalArgumentException(STOCK_NOT_EMPTY);
        }
    }

    private void validateStockLessThanZero(Long stock) {
        if (stock < ZERO) {
            throw new IllegalArgumentException(STOCK_LESS_THAN_ZERO);
        }
    }
}
