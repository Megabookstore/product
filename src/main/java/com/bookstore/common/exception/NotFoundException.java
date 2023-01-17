package com.bookstore.common.exception;

import com.bookstore.common.constant.ErrorCode;

public class NotFoundException extends IllegalArgumentException {

    public NotFoundException() {
        super(ErrorCode.NOT_FOUND_ERROR.getErrorMessage());
    }
}
