package com.bookstore.book.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Contents {

    @Column @Lob
    private String contents;

    public Contents(String contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contents contents1 = (Contents) o;
        return Objects.equals(contents, contents1.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }
}
