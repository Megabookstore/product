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
public class Publisher {

    @Column(nullable = false)
    private String publisher;

    public Publisher(String publisher) {
        validate(publisher);
        this.publisher = publisher;
    }

    private void validate(String publisher) {
        validatePublisherNotEmpty(publisher);
    }

    private void validatePublisherNotEmpty(String publisher) {
        if (Objects.isNull(publisher) || publisher.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.PUBLISHER_NOT_EMPTY.getErrorMessage());
        }
    }

}
