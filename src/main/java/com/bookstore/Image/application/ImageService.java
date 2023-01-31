package com.bookstore.Image.application;

import static com.bookstore.Image.domain.Image.filePath;

import com.bookstore.Image.domain.Image;
import com.bookstore.Image.dto.ImageRequest;
import com.bookstore.Image.dto.ImageResponse;
import com.bookstore.Image.repository.ImageRepository;
import com.bookstore.common.domain.Name;
import com.bookstore.common.exception.NotFoundException;
import com.bookstore.product.domain.Product;
import com.bookstore.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public ImageResponse register(ImageRequest imageRequest) {
        Product product = productFindById(imageRequest.getProductId());
        Image image = imageRepository.save(imageRequest.toImage(product));

        return new ImageResponse(image);
    }

    public ImageResponse update(Long imageId, ImageRequest imageRequest) {
        Image image = imageFindById(imageId);
        Product product = productFindById(imageRequest.getProductId());

        image.update(
            product,
            new Name(imageRequest.getMultipartFile().getOriginalFilename()),
            filePath + imageRequest.getMultipartFile().getOriginalFilename()
        );

        return new ImageResponse(image);
    }

    public ImageResponse delete(Long imageId) {
        Image image = imageFindById(imageId);
        imageRepository.delete(image);

        return new ImageResponse(image);
    }

    private Product productFindById(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(NotFoundException::new);
    }

    private Image imageFindById(Long imageId) {
        return imageRepository.findById(imageId)
            .orElseThrow(NotFoundException::new);
    }
}
