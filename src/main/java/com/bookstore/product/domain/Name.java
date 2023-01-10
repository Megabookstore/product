package com.bookstore.product.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Name {

    private static final String NAME_NOT_EMPTY = "이름은 빈 값을 허용하지 않습니다.";

    @Column(nullable = false)
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNameNotEmpty(name);
    }

    private void validateNameNotEmpty(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException(NAME_NOT_EMPTY);
        }
    }
}
