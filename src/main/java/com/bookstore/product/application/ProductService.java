package com.bookstore.product.application;

import com.bookstore.common.exception.NotFoundException;
import com.bookstore.book.domain.Description;
import com.bookstore.product.domain.Name;
import com.bookstore.product.domain.Price;
import com.bookstore.product.domain.Product;
import com.bookstore.product.domain.ProductStatus;
import com.bookstore.product.domain.Stock;
import com.bookstore.product.dto.ProductRequest;
import com.bookstore.product.dto.ProductResponse;
import com.bookstore.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<ProductResponse> list(Pageable pageable) {
        Page<Product> products = productRepository.findByProductStatusNot(ProductStatus.DELETE, pageable);

        return products.get()
            .map(product -> new ProductResponse(product))
            .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest) {
        Product product = productFindById(id);

        product.update(
            new Description(productRequest.getDescription()),
            new Name(productRequest.getName()),
            new Stock(productRequest.getStock()),
            ProductStatus.valueOf(productRequest.getProductStatus()),
            product.getThumbnail(),
            new Price(productRequest.getPrice())
        );

        return new ProductResponse(product);
    }

    @Transactional
    public ProductResponse delete(Long id) {
        Product product = productFindById(id);
        product.delete();

        return new ProductResponse(product);
    }

    private Product productFindById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(NotFoundException::new);
    }
}
