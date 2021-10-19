package com.tul.inventory.infraestructure.dataprovider.jpa;

import static lombok.AccessLevel.PRIVATE;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.domain.usecases.ports.InventoryPort;
import com.tul.inventory.infraestructure.dataprovider.core.KafkaProducer;
import com.tul.inventory.infraestructure.dataprovider.core.mapper.KafkaMapper;
import com.tul.inventory.infraestructure.dataprovider.jpa.entities.exceptions.ProductNotFoundException;
import com.tul.inventory.infraestructure.dataprovider.jpa.mappers.JpaMapper;
import com.tul.inventory.infraestructure.dataprovider.jpa.repositories.ProductRepository;
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
  final KafkaMapper kafkaMapper;
  final KafkaProducer kafkaProducer;

  @Autowired
  public InventoryProvider(ProductRepository productRepository, JpaMapper jpaMapper,
      KafkaProducer kafkaProducer, KafkaMapper kafkaMapper) {
    this.kafkaProducer = kafkaProducer;
    this.productRepository = productRepository;
    this.jpaMapper = jpaMapper;
    this.kafkaMapper = kafkaMapper;
  }

  @Override
  public UUID saveProduct(Product product) {
    UUID sku = productRepository.save(jpaMapper.toProductJpaEntity(product)).getSku();
    kafkaProducer.sendMessage(kafkaMapper.toMessageProduct(product, sku));
    return sku;
  }

  @Override
  public List<Product> getInventory() {
    return jpaMapper.toProducts(productRepository.findAll());
  }

  @Override
  public void deleteProduct(UUID sku) {
    productRepository.deleteById(sku);
    kafkaProducer.sendDeleteMessage(sku.toString());
  }

  @Override
  public Product getProductBySku(UUID sku) {
    return jpaMapper.toProduct(productRepository.findById(sku)
        .orElseThrow(() -> new ProductNotFoundException("productNotFound")));
  }
}
