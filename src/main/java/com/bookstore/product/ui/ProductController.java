package com.bookstore.product.ui;

import com.bookstore.product.application.ProductService;
import com.bookstore.product.dto.ProductRequest;
import com.bookstore.product.dto.ProductResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> register(@RequestBody final ProductRequest productRequest) {
        ProductResponse productResponse = productService.register(productRequest);
        URI uri = URI.create("/product/" + productResponse.getId());
        return ResponseEntity.created(uri).body(productResponse);
    }
}