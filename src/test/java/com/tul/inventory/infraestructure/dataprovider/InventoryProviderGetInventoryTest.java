package com.tul.inventory.infraestructure.dataprovider;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.jpa.entities.ProductJpaEntity;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class InventoryProviderGetInventoryTest extends InventoryProviderInitializer {

  @Test
  void shouldReturnInventoryWhenGetInventoryIsCalled() {
    List<ProductJpaEntity> dbProducts = List.of(ProductJpaEntity.builder()
        .sku(UUID.randomUUID())
        .build());
    List<Product> products = List.of(Product.builder()
        .sku(UUID.randomUUID())
        .build());
    when(productRepository.findAll()).thenReturn(dbProducts);
    when(jpaMapper.toProducts(dbProducts)).thenReturn(products);
    assertIterableEquals(products, inventoryProvider.getInventory());
  }

}