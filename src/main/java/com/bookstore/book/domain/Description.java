package com.bookstore.book.domain;

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

    private static final String DESCRIPTION_NOT_EMPTY = "설명은 빈 값을 허용하지 않습니다.";
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
            throw new IllegalArgumentException(DESCRIPTION_NOT_EMPTY);
        }
    }
}
