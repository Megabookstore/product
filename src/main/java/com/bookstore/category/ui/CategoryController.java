package com.bookstore.category.ui;

import com.bookstore.category.application.CategoryService;
import com.bookstore.category.dto.CategoryRequest;
import com.bookstore.category.dto.CategoryResponse;
import com.bookstore.category.dto.CategoryUpdateRequest;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> register(
        @RequestBody final CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.register(categoryRequest);
        URI uri = URI.create("/category/" + categoryResponse.getId());
        return ResponseEntity.created(uri).body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list(@RequestParam String parentId) {
        List<CategoryResponse> categoryResponses = categoryService.list(parentId);
        return ResponseEntity.ok().body(categoryResponses);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("categoryId") Long categoryId,
        @RequestBody final CategoryUpdateRequest categoryUpdateRequest) {
        CategoryResponse categoryResponse = categoryService.update(categoryId, categoryUpdateRequest);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> delete(@PathVariable("categoryId") Long categoryId) {
        CategoryResponse categoryResponse = categoryService.delete(categoryId);
        return ResponseEntity.ok().body(categoryResponse);
    }
}
