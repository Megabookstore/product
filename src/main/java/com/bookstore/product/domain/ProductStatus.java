package com.bookstore.product.domain;

import lombok.Getter;

public enum ProductStatus {
    SELL("sell"), OUT_OF_STOCK("out_of_stock"), RESERVATION("reservation"), DELETE("delete");

    @Getter
    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }
}
