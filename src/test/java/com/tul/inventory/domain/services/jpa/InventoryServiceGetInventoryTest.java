package com.tul.inventory.domain.services.jpa;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

class InventoryServiceGetInventoryTest extends InventoryServiceInitializer {

  @Test
  void shouldReturnFoundInventoryWhenGetInventoryIsCalled() {
    List<Product> inventory = List.of(Product.builder()
        .description("description")
        .build());
    List<ProductControllerResponse> convertedInventory = List.of(
        ProductControllerResponse.builder()
            .description("description")
            .build());
    when(inventoryPort.getInventory()).thenReturn(inventory);
    when(serviceMapper.toProductControllerResponseList(inventory)).thenReturn(convertedInventory);
    assertSame(convertedInventory, inventoryService.getInventory());
  }

}