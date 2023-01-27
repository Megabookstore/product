package com.bookstore.category.dto;

import com.bookstore.category.domain.Category;
import com.bookstore.common.domain.Name;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryResponse {

    private Long id;

    private Long parentId;

    private String name;

    private Boolean isHidden;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.parentId = Objects.isNull(category.getCategory()) ? null : category.getCategory().getId();
        this.name = category.getName().getName();
        this.isHidden = category.getIsHidden();
    }
}
