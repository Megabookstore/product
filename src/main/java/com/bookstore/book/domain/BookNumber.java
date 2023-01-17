package com.bookstore.book.domain;

import com.bookstore.common.constant.ErrorCode;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class BookNumber {


    private static final Pattern pattern = Pattern.compile("^[0-9\\-]*$");;

    @Column(length = 20, nullable = false)
    private String bookNumber;

    public BookNumber(String bookNumber) {
        validate(bookNumber);
        this.bookNumber = bookNumber;
    }

    private void validate(String bookNumber) {
        validateBookNumberNotEmpty(bookNumber);
        validateBookNumberPattern(bookNumber);
    }

    private void validateBookNumberNotEmpty(String bookNumber) {
        if (Objects.isNull(bookNumber) || bookNumber.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.BOOK_NUMBER_NOT_EMPTY.getErrorMessage());
        }
    }

    private void validateBookNumberPattern(String bookNumber) {
        if (!pattern.matcher(bookNumber).matches()) {
            throw new IllegalArgumentException(ErrorCode.BOOK_NUMBER_PATTERN.getErrorMessage());
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
        BookNumber that = (BookNumber) o;
        return Objects.equals(bookNumber, that.bookNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookNumber);
    }
}
