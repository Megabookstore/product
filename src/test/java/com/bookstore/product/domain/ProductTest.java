package com.bookstore.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.bookstore.book.domain.Description;
import com.bookstore.common.domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @DisplayName("상품을 업데이트한다.")
    @Test
    void validateNameNotEmpty() {
        Product product = Product.builder()
            .description(new Description("0~4세 알기만 해도 차이를 만드는 육아 대원칙 6"))
            .name(new Name("베싸육아"))
            .stock(new Stock(198L))
            .productStatus(ProductStatus.SELL)
            .thumbnail("https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg")
            .price(new Price(19_800L))
            .build();

        product.update(
            new Description("애자일 소프트웨어 장인 정신"),
            new Name("클린코드"),
            new Stock(1_100L),
            product.getProductStatus(),
            "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg",
            new Price(29_700L)
        );

        assertAll(
            () -> assertThat(product.getDescription()).isEqualTo(new Description("애자일 소프트웨어 장인 정신")),
            () -> assertThat(product.getName()).isEqualTo(new Name("클린코드")),
            () -> assertThat(product.getStock()).isEqualTo(new Stock(1_100L)),
            () -> assertThat(product.getThumbnail()).isEqualTo("https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg"),
            () -> assertThat(product.getPrice()).isEqualTo(new Price(29_700L))
        );
    }
}