package com.bookstore.book.acceptance;

import com.bookstore.book.dto.BookRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class BookRestAssured {

    public static ExtractableResponse<Response> 도서_생성_요청(BookRequest bookRequest) {
        return RestAssured
            .given().log().all()
            .body(bookRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/book")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 도서_상세_조회_요청(Long id) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/book/" + id)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 도서_수정_요청(Long id, BookRequest bookRequest) {
        return RestAssured
            .given().log().all()
            .body(bookRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().put("/book/" + id)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 도서_삭제_요청(Long id) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().delete("/book/" + id)
            .then().log().all()
            .extract();
    }
}
