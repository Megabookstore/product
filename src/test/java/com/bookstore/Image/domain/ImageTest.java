package com.bookstore.Image.domain;

import static com.bookstore.Image.domain.Image.filePath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.bookstore.book.domain.Description;
import com.bookstore.common.domain.Name;
import com.bookstore.product.domain.Price;
import com.bookstore.product.domain.Product;
import com.bookstore.product.domain.ProductStatus;
import com.bookstore.product.domain.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTest {

    @DisplayName("이미지를 업데이트한다.")
    @Test
    void update() {
        Product product = Product.builder()
            .description(new Description("0~4세 알기만 해도 차이를 만드는 육아 대원칙 6"))
            .name(new Name("베싸육아"))
            .stock(new Stock(198L))
            .productStatus(ProductStatus.SELL)
            .thumbnail("https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg")
            .price(new Price(19_800L))
            .build();
        Product newProduct = Product.builder()
            .description(new Description("애자일 소프트웨어 장인 정신"))
            .name(new Name("클린코드"))
            .stock(new Stock(1_100L))
            .productStatus(ProductStatus.SELL)
            .thumbnail("https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg")
            .price(new Price(29_700L))
            .build();

        Image image = Image.builder()
            .product(product)
            .imageName(new Name("베싸육아.jpg"))
            .imagePath(filePath + "/베싸육아.jpg")
            .build();

        image.update(
            newProduct,
            new Name("클린코드.jpg"),
            filePath + "/클린코드.jpg"
        );

        assertAll(
            () -> assertThat(image.getProduct().getName()).isEqualTo(new Name("클린코드")),
            () -> assertThat(image.getImageName()).isEqualTo(new Name("클린코드.jpg")),
            () -> assertThat(image.getImagePath()).isEqualTo(filePath + "/클린코드.jpg")
        );
    }
}