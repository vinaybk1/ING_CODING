package com.ing.kata.banking.execption;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BankingExceptionAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BankingException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
                        (BankingException ex, WebRequest request) 
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getErrMsg());
        ErrorResponse error = new ErrorResponse(ex.getErrCode(), details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
