package com.bookstore.product.acceptance;

import com.bookstore.product.dto.ProductRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class ProductRestAssured {

    public static ExtractableResponse<Response> 상품_생성_요청(ProductRequest productRequest) {
        return RestAssured
            .given().log().all()
            .body(productRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/product")
            .then().log().all()
            .extract();
    }
}
