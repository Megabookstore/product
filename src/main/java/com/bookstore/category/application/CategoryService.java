package com.bookstore.category.application;

import com.bookstore.category.domain.Category;
import com.bookstore.category.dto.CategoryRequest;
import com.bookstore.category.dto.CategoryResponse;
import com.bookstore.category.dto.CategoryUpdateRequest;
import com.bookstore.category.repository.CategoryRepository;
import com.bookstore.common.domain.Name;
import com.bookstore.common.exception.NotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private Category parentCategory;

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponse register(CategoryRequest categoryRequest) {
        if(Objects.isNull(categoryRequest.getParentCategoryId())) {
            Category category = categoryRepository.save(
                categoryRequest.toCategory(null));

            return new CategoryResponse(category);

        }
        Optional<Category> parentCategory = categoryRepository.findById(
            categoryRequest.getParentCategoryId());
        Category category = categoryRepository.save(
            categoryRequest.toCategory(parentCategory.get()));

        return new CategoryResponse(category);
    }

    public List<CategoryResponse> list(String parentId) {
        List<Category> categories;
        if(parentId.isEmpty() || Objects.isNull(parentId)) {
            categories = categoryRepository.findByCategoryIsNull();

            return categoriesToDto(categories);
        }
        categories = categoryRepository.findByCategory(categoryRepository.findById(Long.valueOf(parentId)).get());

        return categoriesToDto(categories);
    }

    @Transactional
    public CategoryResponse update(Long categoryId, CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryFindById(categoryId);

        category.update(
            new Name(categoryUpdateRequest.getName()),
            categoryUpdateRequest.getIsHidden()
        );
        return new CategoryResponse(category);
    }

    @Transactional
    public CategoryResponse delete(Long categoryId) {
        Category category = categoryFindById(categoryId);
        categoryRepository.delete(category);

        return new CategoryResponse(category);
    }

    public List<CategoryResponse> categoriesToDto(List<Category> categories) {
        return categories.stream()
            .map(category -> new CategoryResponse(category))
            .collect(Collectors.toList());
    }

    public Category categoryFindById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }
}

