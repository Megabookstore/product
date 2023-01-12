package com.bookstore.product.domain;

import com.bookstore.common.domain.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(
            o)) {
            return false;
        }
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
