package com.bookstore.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryUpdateRequest {

    private String name;

    private Boolean isHidden;
}
