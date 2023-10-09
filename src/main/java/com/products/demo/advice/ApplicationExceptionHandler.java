package com.products.demo.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.products.demo.exception.BadRequestException;
import com.products.demo.exception.SimilarProductsNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public Map<String, String> handleBusinessException(HttpClientErrorException.NotFound ex) {
		return this.mapResponse(ex);
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SimilarProductsNotFoundException.class)
    public Map<String, String> handleBusinessException(SimilarProductsNotFoundException ex) {
		return this.mapResponse(ex);
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleException(Exception ex) {
        return this.mapResponse(ex);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, String> handleException(BadRequestException ex) {
        return this.mapResponse(ex);
    }
	
	public Map<String, String> mapResponse(Exception ex) {
		Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
	}

}
