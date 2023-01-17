package com.bookstore.common.constant;

public enum ErrorCode {

    NAME_NOT_EMPTY("이름은 빈 값을 허용하지 않습니다."),
    STOCK_LESS_THAN_ZERO("재고의 수량은 0보다 작을 수 없습니다."),
    STOCK_NOT_EMPTY("재고는 빈 값을 허용하지 않습니다."),
    PRICE_LESS_THAN_ZERO("가격은 0원보다 작을 수 없습니다."),
    DESCRIPTION_NOT_EMPTY("설명은 빈 값을 허용하지 않습니다."),
    PUBLISHER_NOT_EMPTY("출판사는 빈 값을 허용하지 않습니다."),
    TITLE_NOT_EMPTY("제목은 빈 값을 허용하지 않습니다."),
    BOOK_NUMBER_NOT_EMPTY("국제표준도서번호(ISBN)은 빈 값을 허용하지 않습니다."),
    BOOK_NUMBER_PATTERN("국제표준도서번호(ISBN)의 유효하지 않은 규칙입니다."),
    NOT_FOUND_ERROR("데이터가 존재하지 않습니다.");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
