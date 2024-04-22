package com.app.Controller;

import com.app.Exception.ProductBadRequest;
import com.app.Exception.ProductNotFoundException;
import com.app.Exception.Responce.ProductErrorResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponce> handlerProductNotFound(ProductNotFoundException exception) {
        ProductErrorResponce responce = new ProductErrorResponce();
        responce.setCode(HttpStatus.NOT_FOUND.value());
        responce.setMessage(exception.getMessage());
        responce.setTimeStamp(System.currentTimeMillis());

        return  new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponce>hndleBadRequest(ProductBadRequest exception){
        ProductErrorResponce responce = new ProductErrorResponce();
        responce.setCode(HttpStatus.BAD_REQUEST.value());;
        responce.setMessage(exception.getMessage());
        responce.setTimeStamp(System.currentTimeMillis());
         return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);

    }
}
