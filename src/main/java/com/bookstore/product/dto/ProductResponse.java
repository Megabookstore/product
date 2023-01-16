package com.bookstore.product.dto;

import com.bookstore.book.domain.Description;
import com.bookstore.product.domain.Name;
import com.bookstore.product.domain.Price;
import com.bookstore.product.domain.Product;
import com.bookstore.product.domain.ProductStatus;
import com.bookstore.product.domain.Stock;
import java.math.BigDecimal;
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
        this.description = description(product).getDescription();
        this.name = name(product).getName();
        this.stock = stock(product).getStock();
        this.productStatus = productStatus(product).getValue();
        this.thumbnail = product.getThumbnail();
        this.price = getPrice(product).longValue();
    }

    public Description description(Product product) {
        return product.getDescription();
    }

    public Name name(Product product) {
        return product.getName();
    }

    public Stock stock(Product product) {
        return product.getStock();
    }

    public ProductStatus productStatus(Product product) {
        return product.getProductStatus();
    }

    public Price price(Product product) {
        return product.getPrice();
    }

    public BigDecimal getPrice(Product product) {
        return price(product).getPrice();
    }
}
