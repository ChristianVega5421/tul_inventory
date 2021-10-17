package com.tul.inventory.domain.services.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InventoryServiceGetProductBySkuTest extends InventoryServiceInitializer {

  @Test
  void shouldReturnProductBySkuWhenServiceIsCalled() {
    UUID sku = UUID.randomUUID();
    Product product = Product.builder()
        .description("description")
        .build();
    ProductControllerResponse controllerResponse = ProductControllerResponse.builder()
        .description(product.getDescription())
        .build();
    when(inventoryPort.getProductBySku(sku)).thenReturn(product);
    when(serviceMapper.toProductControllerResponse(product)).thenReturn(controllerResponse);
    Assertions.assertEquals(controllerResponse, inventoryService.getProductBySku(sku));
  }
}