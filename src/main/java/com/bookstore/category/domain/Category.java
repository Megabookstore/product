package com.bookstore.category.domain;

import com.bookstore.common.domain.BaseEntity;
import com.bookstore.common.domain.Name;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category category;

    @Embedded
    private Name name;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isHidden;

    public void update(Name name, boolean isHidden) {
        this.name = name;
        this.isHidden = isHidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category1 = (Category) o;
        return isHidden == category1.isHidden && Objects.equals(id, category1.id)
            && Objects.equals(category, category1.category) && Objects.equals(name,
            category1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, isHidden);
    }
}
