package com.bookstore.Image.domain;

import com.bookstore.common.domain.BaseEntity;
import com.bookstore.common.domain.Name;
import com.bookstore.product.domain.Product;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Image extends BaseEntity {

    @Value("${filePath.image}")
    public static String filePath;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private Name imageName;

    @Column(nullable = false)
    private String imagePath;

    public void update(Product product, Name imageName, String imagePath) {
        this.product = product;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(product,
            image.product) && Objects.equals(imageName, image.imageName)
            && Objects.equals(imagePath, image.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, imageName, imagePath);
    }
}
