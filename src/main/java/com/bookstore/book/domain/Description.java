package com.bookstore.book.domain;

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
public class Description {

    @Column(length = 1000, nullable = false)
    private String description;

    public Description(String description) {
        validate(description);
        this.description = description;
    }

    private void validate(String description) {
        validateDescriptionNotEmpty(description);
    }

    private void validateDescriptionNotEmpty(String description) {
        if (Objects.isNull(description) || description.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.DESCRIPTION_NOT_EMPTY.getErrorMessage());
        }
    }
}
