package com.tul.inventory.infraestructure.dataprovider.jpa.entities.exceptions;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message) {
    super(message);
  }

}
