package com.tul.inventory.infraestructure.dataprovider;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.jpa.entities.ProductJpaEntity;
import com.tul.inventory.infraestructure.dataprovider.jpa.entities.exceptions.ProductNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class InventoryProviderGetProductBySkuTest extends InventoryProviderInitializer {

  @Test
  void shouldThrowProductNotFoundExceptionWhenProductIsNotFound() {
    UUID sku = UUID.randomUUID();
    when(productRepository.findById(sku)).thenReturn(Optional.empty());
    assertThrows(ProductNotFoundException.class, () -> inventoryProvider.getProductBySku(sku));
  }

  @Test
  void shouldReturnProductWhenProductIsFound() {
    UUID sku = UUID.randomUUID();
    ProductJpaEntity productJpa = ProductJpaEntity.builder()
        .description("description")
        .build();
    Product product = Product.builder()
        .description("description")
        .build();
    when(productRepository.findById(sku)).thenReturn(Optional.of(productJpa));
    when(jpaMapper.toProduct(productJpa)).thenReturn(product);
    assertSame(product, inventoryProvider.getProductBySku(sku));
  }

}