package com.bookstore.product.acceptance;

import static com.bookstore.product.acceptance.ProductRestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.bookstore.common.AcceptanceTest;
import com.bookstore.product.domain.ProductStatus;
import com.bookstore.product.dto.ProductRequest;
import com.bookstore.product.dto.ProductResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("상품 관련 인수 테스트")
public class ProductAcceptanceTest extends AcceptanceTest {

    private ProductRequest productRequest;

    @BeforeEach
    public void setUp() {
        productRequest = new ProductRequest(
            "애자일 소프트웨어 장인 정신",
            "클린코드",
            1_100L,
            "SELL",
            "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg",
            29_700L
        );
    }

    @DisplayName("상품을 생성한다.")
    @Test
    void registerProduct() {
        ExtractableResponse<Response> response = 상품_생성_요청(productRequest);

        상품_생성됨(response);
    }

    @DisplayName("상품을 수정한다")
    @Test
    void updateProduct() {
        ProductResponse productResponse = 상품_생성_요청(productRequest).as(ProductResponse.class);

        ProductRequest productUpdateRequest = new ProductRequest(
            "0~4세 알기만 해도 차이를 만드는 육아 대원칙 6",
            "베싸육아",
            198L,
            "SELL",
            "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg",
            29_700L
        );

        ExtractableResponse<Response> response = 상품_수정_요청(productResponse.getId(), productUpdateRequest);

        상품_수정됨(response, productRequest);
    }

    private static void 상품_생성됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static void 상품_수정됨(ExtractableResponse response, ProductRequest productRequest) {
        ProductResponse result = response.as(ProductResponse.class);

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(result.getDescription()).isEqualTo(productRequest.getDescription()),
            () -> assertThat(result.getName()).isEqualTo(productRequest.getName()),
            () -> assertThat(result.getStock()).isEqualTo(productRequest.getStock()),
            () -> assertThat(result.getProductStatus()).isEqualTo(productRequest.getProductStatus()),
            () -> assertThat(result.getThumbnail()).isEqualTo(productRequest.getThumbnail()),
            () -> assertThat(result.getPrice()).isEqualTo(productRequest.getPrice())
        );
    }
}
