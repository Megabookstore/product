package com.bookstore.product.domain;

import com.bookstore.common.constant.ErrorCode;
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
            throw new IllegalArgumentException(ErrorCode.STOCK_NOT_EMPTY.getErrorMessage());
        }
    }

    private void validateStockLessThanZero(Long stock) {
        if (stock < ZERO) {
            throw new IllegalArgumentException(ErrorCode.STOCK_LESS_THAN_ZERO.getErrorMessage());
        }
    }
}
