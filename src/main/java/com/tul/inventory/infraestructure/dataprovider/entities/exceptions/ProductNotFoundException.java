package com.tul.inventory.infraestructure.dataprovider.entities.exceptions;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message) {
    super(message);
  }

}
