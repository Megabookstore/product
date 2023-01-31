package com.bookstore.Image.dto;

import static com.bookstore.Image.domain.Image.filePath;

import com.bookstore.Image.domain.Image;
import com.bookstore.common.domain.Name;
import com.bookstore.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImageRequest {

    private Long productId;

    private MultipartFile multipartFile;

    public Image toImage(Product product) {
        return Image.builder()
            .product(product)
            .imageName(new Name(multipartFile.getOriginalFilename()))
            .imagePath(filePath + multipartFile.getOriginalFilename())
            .build();
    }
}
