package com.bookstore.product.dto;

import com.bookstore.product.domain.Description;
import com.bookstore.product.domain.Name;
import com.bookstore.product.domain.Price;
import com.bookstore.product.domain.Product;
import com.bookstore.product.domain.ProductStatus;
import com.bookstore.product.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductRequest {

    private String description;

    private String name;

    private Long stock;

    private String productStatus;

    private String thumbnail;

    private Long price;

    public Product toProduct() {
        return Product.builder()
            .description(new Description(description))
            .name(new Name(name))
            .stock(new Stock(stock))
            .productStatus(ProductStatus.valueOf(productStatus))
            .thumbnail(thumbnail)
            .price(new Price(price))
            .build();
    }
}
