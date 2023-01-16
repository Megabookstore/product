package com.bookstore.product.acceptance;

import static com.bookstore.product.acceptance.ProductRestAssured.상품_목록_조회_요청;
import static com.bookstore.product.acceptance.ProductRestAssured.상품_삭제_요청;
import static com.bookstore.product.acceptance.ProductRestAssured.상품_생성_요청;
import static com.bookstore.product.acceptance.ProductRestAssured.상품_수정_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.bookstore.common.AcceptanceTest;
import com.bookstore.product.dto.ProductRequest;
import com.bookstore.product.dto.ProductResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("상품 관련 인수 테스트")
public class ProductAcceptanceTest extends AcceptanceTest {

    private ProductRequest productRequest;
    private ProductRequest productDeleteRequest;

    @BeforeEach
    public void setUp() {
        super.setUp();
        productRequest = new ProductRequest(
            "애자일 소프트웨어 장인 정신",
            "클린코드",
            1_100L,
            "SELL",
            "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791198129109.jpg",
            29_700L
        );
        productDeleteRequest = new ProductRequest(
            "애자일 소프트웨어 장인 정신",
            "클린코드",
            1_100L,
            "DELETE",
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

    @DisplayName("상품을 확인한다.")
    @Test
    void listProduct() {
        ProductResponse productResponse = 상품_생성_요청(productRequest).as(ProductResponse.class);
        ProductResponse productDeleteResponse = 상품_생성_요청(productDeleteRequest).as(ProductResponse.class);;

        ExtractableResponse<Response> response = 상품_목록_조회_요청();

        상품_응답됨(response);
        상품_목록_확인됨(response, Arrays.asList(productResponse.getId()));
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

        상품_수정됨(response, productUpdateRequest);
    }

    @DisplayName("상품을 삭제한다")
    @Test
    void deleteProduct() {
        ProductResponse productResponse = 상품_생성_요청(productRequest).as(ProductResponse.class);

        ExtractableResponse<Response> response = 상품_삭제_요청(productResponse.getId());

        상품_응답됨(response);
    }

    private static void 상품_생성됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static void 상품_응답됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static void 상품_목록_확인됨(ExtractableResponse response, List<Long> productIds) {
        List<Long> resultIds = response.jsonPath().getList(".", ProductResponse.class)
            .stream()
            .map(ProductResponse::getId)
            .collect(Collectors.toList());

        assertThat(resultIds).containsAll(productIds);
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
