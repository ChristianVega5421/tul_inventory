package com.tul.inventory.infraestructure.dataprovider.core.utils;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.core.entities.KafkaReportedProduct;
import java.util.Map;

public class DeletedProductCalculator implements QuantityCalculator {

  @Override
  public Map<String, Integer> calculateQuantity(Product product,
      KafkaReportedProduct reportedProduct, boolean isExistingOrder) {
    return Map.of("stock", product.getStock() + reportedProduct.getQuantity(),
        "reserved", product.getReserved() - reportedProduct.getQuantity());
  }
}
