package com.bookstore.product.domain;

import static com.bookstore.common.constant.ErrorCode.NAME_NOT_EMPTY;

import com.bookstore.common.constant.ErrorCode;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Name {


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
            throw new IllegalArgumentException(NAME_NOT_EMPTY.getErrorMessage());
        }
    }
}
