package com.bookstore.cateogry.acceptance;

import com.bookstore.book.dto.BookRequest;
import com.bookstore.category.dto.CategoryRequest;
import com.bookstore.category.dto.CategoryUpdateRequest;
import com.bookstore.product.dto.ProductRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class CategoryRestAssured {

    public static ExtractableResponse<Response> 카테고리_생성_요청(CategoryRequest categoryRequest) {
        return RestAssured
            .given().log().all()
            .body(categoryRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/category")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 카테고리_조회_요청(String parentId) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/category?parentId=" + parentId)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 카테고리_수정_요청(Long categoryId, CategoryUpdateRequest categoryUpdateRequest) {
        return RestAssured
            .given().log().all()
            .body(categoryUpdateRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().put("/category/" + categoryId)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 카테고리_삭제_요청(Long categoryId) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().delete("/category/" + categoryId)
            .then().log().all()
            .extract();
    }
}
