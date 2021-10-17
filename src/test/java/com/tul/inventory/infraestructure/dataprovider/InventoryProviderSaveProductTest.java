package com.tul.inventory.infraestructure.dataprovider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.entities.ProductJpaEntity;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class InventoryProviderSaveProductTest extends InventoryProviderInitializer {

  @Test
  void shouldReturnUUIDWhenProductIsSaved() {
    Product product = Product.builder()
        .description("description")
        .build();
    ProductJpaEntity productJpa = ProductJpaEntity.builder()
        .description("description")
        .build();
    ProductJpaEntity savedProduct = ProductJpaEntity.builder()
        .description("description")
        .sku(UUID.randomUUID())
        .build();
    when(jpaMapper.toProductJpaEntity(product)).thenReturn(productJpa);
    when(productRepository.save(productJpa)).thenReturn(savedProduct);
    assertEquals(savedProduct.getSku(), inventoryProvider.saveProduct(product));
  }

}