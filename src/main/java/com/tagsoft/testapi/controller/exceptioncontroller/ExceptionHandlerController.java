package com.tagsoft.testapi.controller.exceptioncontroller;

import com.tagsoft.testapi.exception.DownloadDataParseException;
import com.tagsoft.testapi.exception.ExecuteRequestHttpClientException;
import com.tagsoft.testapi.exception.IllegalTimestampException;
import com.tagsoft.testapi.exception.ResourceUnavailableException;
import com.tagsoft.testapi.model.dto.generic.ApiResponseWrapper;
import com.tagsoft.testapi.model.dto.generic.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({DownloadDataParseException.class, ExecuteRequestHttpClientException.class,
            IllegalTimestampException.class, ResourceUnavailableException.class})
    public ResponseEntity<ApiResponseWrapper> restClientError(RuntimeException ex) {
        return badRequest(new ErrorDto(ex.getMessage(), Collections.emptyList()));
    }


    private ResponseEntity<ApiResponseWrapper> badRequest(ErrorDto error) {
        return ResponseEntity.badRequest().body(new ApiResponseWrapper<>(error));
    }
}
