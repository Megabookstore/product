package com.bookstore.book.acceptance;

import static com.bookstore.book.acceptance.BookRestAssured.도서_삭제_요청;
import static com.bookstore.book.acceptance.BookRestAssured.도서_상세_조회_요청;
import static com.bookstore.book.acceptance.BookRestAssured.도서_생성_요청;
import static com.bookstore.book.acceptance.BookRestAssured.도서_수정_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.bookstore.book.dto.BookRequest;
import com.bookstore.book.dto.BookResponse;
import com.bookstore.common.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("도서 관련 인수 테스트")
public class BookAcceptanceTest extends AcceptanceTest {

    private BookRequest bookRequest;

    @BeforeEach
    public void setUp() {
        super.setUp();
        bookRequest = new BookRequest(
            "979-11-62245-26-2",
            "헤드 퍼스트 디자인 패턴",
            "유지관리가 편리한 객체지향 소프트웨어 만들기!",
            "00장 들어가며 | 이 책을 읽는 방법",
            "한빛미디어"
        );
    }

    @DisplayName("도서를 생성한다.")
    @Test
    void registerBook() {
        ExtractableResponse<Response> response = 도서_생성_요청(bookRequest);

        도서_생성됨(response);
    }

    @DisplayName("도서를 확인한다.")
    @Test
    void detailBook() {
        BookResponse bookResponse = 도서_생성_요청(bookRequest).as(BookResponse.class);

        ExtractableResponse<Response> response = 도서_상세_조회_요청(bookResponse.getId());

        도서_응답됨(response);
        도서_상세_확인됨(response, bookResponse.getId());
    }

    @DisplayName("도서를 수정한다.")
    @Test
    void updateBook() {
        BookResponse bookResponse = 도서_생성_요청(bookRequest).as(BookResponse.class);

        BookRequest bookUpdateRequest = new BookRequest(
            "979-11-95444-95-3",
            "GoF의 디자인 패턴 :재사용성을 지닌 객체지향 소프트웨어의 핵심요소",
            "이 책은 디자인 패턴을 다룬 이론서입니다. 디자인 패턴의 기초적이고 전반적인 내용을 학습할 수 있습니다.",
            "Chapter1 서론",
            "프로텍미디어"
        );
        ExtractableResponse<Response> response = 도서_수정_요청(bookResponse.getId(), bookUpdateRequest);

        도서_수정됨(response, bookUpdateRequest);
    }

    @DisplayName("도서를 삭제한다.")
    @Test
    void deleteBook() {
        BookResponse bookResponse = 도서_생성_요청(bookRequest).as(BookResponse.class);

        ExtractableResponse<Response> response = 도서_삭제_요청(bookResponse.getId());

        도서_응답됨(response);
    }

    private static void 도서_생성됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static void 도서_응답됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static void 도서_상세_확인됨(ExtractableResponse response, Long bookId) {
        BookResponse result = response.as(BookResponse.class);

        assertThat(result.getId()).isEqualTo(bookId);
    }

    private static void 도서_수정됨(ExtractableResponse response, BookRequest bookRequest) {
        BookResponse result = response.as(BookResponse.class);

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(result.getBookNumber()).isEqualTo(bookRequest.getBookNumber()),
            () -> assertThat(result.getTitle()).isEqualTo(bookRequest.getTitle()),
            () -> assertThat(result.getDescription()).isEqualTo(bookRequest.getDescription()),
            () -> assertThat(result.getContents()).isEqualTo(bookRequest.getContents()),
            () -> assertThat(result.getPublisher()).isEqualTo(bookRequest.getPublisher())
        );
    }

}
