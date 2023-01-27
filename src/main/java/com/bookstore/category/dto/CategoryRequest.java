package com.bookstore.category.dto;

import com.bookstore.category.domain.Category;
import com.bookstore.common.domain.Name;
import javax.persistence.Column;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryRequest {

    private Long categoryId;

    private Long parentCategoryId;

    private String name;

    private Boolean isHidden;

    public Category toCategory(Category parentCategory) {
        return Category.builder()
            .category(parentCategory)
            .name(new Name(name))
            .isHidden(isHidden)
            .build();
    }
}
