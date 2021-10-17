package com.tul.inventory.domain.services.jpa;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import com.tul.inventory.domain.entities.Product;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class InventoryServiceSaveProductTest extends InventoryServiceInitializer {

  @Test
  void shouldReturnSavedProductUUIDWhenSavedMethodIsCalled() {
    Product product = Product.builder()
        .price(BigDecimal.valueOf(1.0))
        .build();
    UUID dbId = UUID.randomUUID();
    when(inventoryPort.saveProduct(product)).thenReturn(dbId);
    assertSame(dbId, inventoryService.saveProduct(product));
  }

}