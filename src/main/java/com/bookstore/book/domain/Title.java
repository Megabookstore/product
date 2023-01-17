package com.bookstore.book.domain;

import com.bookstore.common.constant.ErrorCode;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Title {

    @Column(nullable = false)
    private String title;

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    private void validate(String title) {
        validateTitleNotEmpty(title);
    }

    private void validateTitleNotEmpty(String title) {
        if (Objects.isNull(title) || title.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.TITLE_NOT_EMPTY.getErrorMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
