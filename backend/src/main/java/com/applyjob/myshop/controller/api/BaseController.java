package com.applyjob.myshop.controller.api;

import com.applyjob.myshop.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    protected <T> ResponseEntity<ApiResponse<T>> error(
            HttpStatus status,
            String message
    ) {

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.error(
                                status.value(),
                                message
                        )
                );
    }

}
