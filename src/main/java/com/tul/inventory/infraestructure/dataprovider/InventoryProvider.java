package com.tul.inventory.infraestructure.dataprovider;

import static lombok.AccessLevel.PRIVATE;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.domain.usecases.ports.InventoryPort;
import com.tul.inventory.infraestructure.dataprovider.entities.exceptions.ProductNotFoundException;
import com.tul.inventory.infraestructure.dataprovider.mappers.JpaMapper;
import com.tul.inventory.infraestructure.dataprovider.repositories.ProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@FieldDefaults(level = PRIVATE)
@Service
public class InventoryProvider implements InventoryPort {

  final ProductRepository productRepository;
  final JpaMapper jpaMapper;

  @Autowired
  public InventoryProvider(ProductRepository productRepository, JpaMapper jpaMapper) {
    this.productRepository = productRepository;
    this.jpaMapper = jpaMapper;
  }

  @Override
  public UUID saveProduct(Product product) {
    return productRepository.save(jpaMapper.toProductJpaEntity(product)).getSku();
  }

  @Override
  public List<Product> getInventory() {
    return jpaMapper.toProducts(productRepository.findAll());
  }

  @Override
  public void deleteProduct(UUID sku) {
    productRepository.deleteById(sku);
  }

  @Override
  public Product getProductBySku(UUID sku) {
    return jpaMapper.toProduct(productRepository.findById(sku)
        .orElseThrow(() -> new ProductNotFoundException("productNotFound")));
  }
}
