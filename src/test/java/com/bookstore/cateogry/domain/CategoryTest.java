package com.bookstore.cateogry.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.bookstore.category.domain.Category;
import com.bookstore.category.dto.CategoryRequest;
import com.bookstore.common.domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class CategoryTest {

    @DisplayName("카테고리를 업데이트한다.")
    @Test
    void update() {
        Category category = Category.builder()
            .id(1L)
            .category(null)
            .name(new Name("국내도서"))
            .isHidden(false)
            .build();

        category.update(
            new Name("해외도서"),
            true
        );

        assertAll(
            () -> assertThat(category.getName()).isEqualTo(new Name("해외도서")),
            () -> assertThat(category.getIsHidden()).isEqualTo(true)
        );
    }
}
