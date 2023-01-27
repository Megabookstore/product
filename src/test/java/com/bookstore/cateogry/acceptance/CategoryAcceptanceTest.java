package com.bookstore.cateogry.acceptance;

import static com.bookstore.cateogry.acceptance.CategoryRestAssured.카테고리_삭제_요청;
import static com.bookstore.cateogry.acceptance.CategoryRestAssured.카테고리_생성_요청;
import static com.bookstore.cateogry.acceptance.CategoryRestAssured.카테고리_수정_요청;
import static com.bookstore.cateogry.acceptance.CategoryRestAssured.카테고리_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.bookstore.category.dto.CategoryRequest;
import com.bookstore.category.dto.CategoryResponse;
import com.bookstore.category.dto.CategoryUpdateRequest;
import com.bookstore.common.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("카테고리 관련 인수 테스트")
public class CategoryAcceptanceTest extends AcceptanceTest {

    private CategoryRequest parentCategoryRequest;
    private CategoryRequest categoryRequest;

    @BeforeEach
    public void setUp() {
        super.setUp();
        parentCategoryRequest = new CategoryRequest(
            1L,
            null,
            "국내도서",
            false
        );

        categoryRequest = new CategoryRequest(
            2L,
            1L,
            "소설",
            false
        );
    }

    @DisplayName("카테코리를 생성한다.")
    @Test
    void registerParentCategory() {
        ExtractableResponse<Response> response = 카테고리_생성_요청(parentCategoryRequest);

        카테고리_생성됨(response);
    }

    @DisplayName("하위카테고리를 생성한다.")
    @Test
    void registerCategory() {
        ExtractableResponse<Response> parentResponse = 카테고리_생성_요청(parentCategoryRequest);
        ExtractableResponse<Response> response = 카테고리_생성_요청(categoryRequest);

        카테고리_생성됨(parentResponse);
        카테고리_생성됨(response);
    }

    @DisplayName("카테고리를 확인한다.")
    @Test
    void listCategory() {
        CategoryResponse parentCategoryResponse = 카테고리_생성_요청(parentCategoryRequest).as(CategoryResponse.class);
        CategoryResponse categoryResponse = 카테고리_생성_요청(categoryRequest).as(CategoryResponse.class);

        ExtractableResponse<Response> parentResponse = 카테고리_조회_요청("");
        ExtractableResponse<Response> response = 카테고리_조회_요청(parentCategoryResponse.getId().toString());

        카테고리_응답됨(parentResponse);
        카테고리_응답됨(response);

        카테고리_목록_확인됨(parentResponse, Arrays.asList(parentCategoryResponse.getId()));
        카테고리_목록_확인됨(response, Arrays.asList(categoryResponse.getId()));
    }

    @DisplayName("카테고리를 수정한다")
    @Test
    void updateCategory() {
        CategoryResponse parentCategoryResponse = 카테고리_생성_요청(parentCategoryRequest).as(CategoryResponse.class);

        CategoryUpdateRequest categoryUpdateRequest = new CategoryUpdateRequest(
            "해외도서",
            true
        );

        ExtractableResponse<Response> response = 카테고리_수정_요청(parentCategoryResponse.getId(), categoryUpdateRequest);

        카테고리_수정됨(response, categoryUpdateRequest);
    }

    @DisplayName("카테고리를 삭제한다.")
    @Test
    void deleteCategory() {
        CategoryResponse parentCategoryResponse = 카테고리_생성_요청(parentCategoryRequest).as(CategoryResponse.class);

        ExtractableResponse<Response> response = 카테고리_삭제_요청(parentCategoryResponse.getId());

        카테고리_응답됨(response);
    }

    private static void 카테고리_생성됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static void 카테고리_응답됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static void 카테고리_목록_확인됨(ExtractableResponse response, List<Long> categoryIds) {
        List<Long> resultIds = response.jsonPath().getList(".", CategoryResponse.class)
            .stream()
            .map(CategoryResponse::getId)
            .collect(Collectors.toList());

        assertThat(resultIds).containsAll(categoryIds);
    }

    private static void 카테고리_수정됨(ExtractableResponse response, CategoryUpdateRequest categoryUpdateRequest) {
        CategoryResponse result = response.as(CategoryResponse.class);

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(result.getName()).isEqualTo(categoryUpdateRequest.getName()),
            () -> assertThat(result.getIsHidden()).isEqualTo(categoryUpdateRequest.getIsHidden())
        );
    }
}
