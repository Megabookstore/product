package com.bookstore.product.application;

import com.bookstore.product.domain.Product;
import com.bookstore.product.dto.ProductRequest;
import com.bookstore.product.dto.ProductResponse;
import com.bookstore.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse register(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.toProduct());
        return new ProductResponse(product);
    }
}
