package com.bookstore.product.dto;

import com.bookstore.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductResponse {

    private Long id;

    private String description;

    private String name;

    private Long stock;

    private String productStatus;

    private String thumbnail;

    private Long price;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.description = product.getDescription().getDescription();
        this.name = product.getName().getName();
        this.stock = product.getStock().getStock();
        this.productStatus = product.getProductStatus().getValue();
        this.thumbnail = product.getThumbnail();
        this.price = product.getPrice().getPrice().longValue();
    }
}
