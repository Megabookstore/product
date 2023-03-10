package com.bookstore.product.ui;

import com.bookstore.product.application.ProductService;
import com.bookstore.product.dto.ProductRequest;
import com.bookstore.product.dto.ProductResponse;
import java.net.URI;
import java.util.List;
import javax.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> register(
        @RequestBody final ProductRequest productRequest) {
        ProductResponse productResponse = productService.register(productRequest);
        URI uri = URI.create("/product/" + productResponse.getId());
        return ResponseEntity.created(uri).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> list(
        @RequestParam(required = false, defaultValue = "0") @Max(value = 999) int page,
        @RequestParam(required = false, defaultValue = "10") @Max(value = 100) int size) {
        List<ProductResponse> productResponses = productService.list(PageRequest.of(page, size));
        return ResponseEntity.ok().body(productResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
        @RequestBody final ProductRequest productRequest,
        @PathVariable("id") Long id) {
        ProductResponse productResponse = productService.update(id, productRequest);
        return ResponseEntity.ok().body(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Long id) {
        ProductResponse productResponse = productService.delete(id);
        return ResponseEntity.ok().body(productResponse);
    }
}