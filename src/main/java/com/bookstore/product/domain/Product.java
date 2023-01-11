package com.bookstore.product.domain;

import com.bookstore.common.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Description description;

    @Embedded
    private Name name;

    @Embedded
    private Stock stock;

    @Column
    private ProductStatus productStatus;

    @Column(length = 1000)
    private String thumbnail;

    @Embedded
    private Price price;

    public void update(Description description, Name name, Stock stock, ProductStatus productStatus,
        String thumbnail, Price price) {
        this.description = description;
        this.name = name;
        this.stock = stock;
        this.productStatus = productStatus;
        this.thumbnail = thumbnail;
        this.price = price;
    }
}
