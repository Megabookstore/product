package com.bookstore.product.repository;

import com.bookstore.product.domain.Product;
import com.bookstore.product.domain.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByProductStatusNot(ProductStatus productStatus, Pageable pageable);
}
