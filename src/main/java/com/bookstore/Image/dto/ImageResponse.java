package com.bookstore.Image.dto;

import com.bookstore.Image.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImageResponse {

    private Long id;

    private Long productId;

    private String imageName;

    private String imagePath;

    public ImageResponse(Image image) {
        this.id = image.getId();
        this.productId = image.getProduct().getId();
        this.imageName = image.getImageName().getName();
        this.imagePath = image.getImagePath();
    }
}
