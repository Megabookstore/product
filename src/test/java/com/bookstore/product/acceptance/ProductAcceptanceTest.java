package com.bookstore.product.acceptance;

import static com.bookstore.product.acceptance.ProductRestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.bookstore.common.AcceptanceTest;
import com.bookstore.product.domain.ProductStatus;
import com.bookstore.product.dto.ProductRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("상품 관련 인수 테스트")
public class ProductAcceptanceTest extends AcceptanceTest {

    @DisplayName("상품을 생성한다.")
    @Test
    void registerProduct() {
        ProductRequest productRequest = new ProductRequest(
            "애자일 소프트웨어 장인 정신",
            "클린코드",
            1_100L,
            "SELL",
            "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg",
            29_700L
        );

        ExtractableResponse<Response> response = 상품_생성_요청(productRequest);

        상품_생성됨(response);
    }

    private static void 상품_생성됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
