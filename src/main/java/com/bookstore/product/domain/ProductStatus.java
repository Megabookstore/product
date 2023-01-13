package com.bookstore.product.domain;

import lombok.Getter;

public enum ProductStatus {
    SELL("SELL"), OUT_OF_STOCK("OUT_OF_STOCK"), RESERVATION("RESERVATION"), DELETE("DELETE");

    @Getter
    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }
}
