package com.tul.inventory.infraestructure.dataprovider;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class InventoryProviderDeleteProductTest extends InventoryProviderInitializer {

  @Test
  void shouldCallDeleteWhenDeleteProductIsCalled() {
    UUID sku = UUID.randomUUID();
    inventoryProvider.deleteProduct(sku);
    verify(productRepository, times(1)).deleteById(sku);
  }

}