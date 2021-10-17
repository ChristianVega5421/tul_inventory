package com.tul.inventory.domain.services.jpa;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class InventoryServiceDeleteProductTest extends InventoryServiceInitializer {

  @Test
  void shouldCallDeleteProductWhenDeleteProductIsCalled() {
    UUID sku = UUID.randomUUID();
    inventoryService.deleteProduct(sku);
    verify(inventoryPort, times(1)).deleteProduct(sku);
  }

}