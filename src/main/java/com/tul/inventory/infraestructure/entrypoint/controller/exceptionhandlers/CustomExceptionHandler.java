package com.tul.inventory.infraestructure.entrypoint.controller.exceptionhandlers;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.tul.inventory.infraestructure.dataprovider.entities.exceptions.ProductNotFoundException;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ErrorResponse;
import javax.persistence.PersistenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(PersistenceException.class)
  protected ResponseEntity<ErrorResponse> handleJpaException(PersistenceException exception,
      WebRequest request) {
    return ResponseEntity.internalServerError().body(ErrorResponse.builder()
        .errorMessage(exception.getCause().getMessage())
        .build());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleProductNotFoundException(
      ProductNotFoundException exception) {
    return ResponseEntity.status(NOT_FOUND).body(ErrorResponse.builder()
        .errorMessage(exception.getCause().getMessage())
        .build());
  }

}
