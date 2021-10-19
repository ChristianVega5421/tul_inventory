package com.tul.inventory.infraestructure.dataprovider.core.utils;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.core.entities.KafkaReportedProduct;
import java.util.Map;

public class ReservedProductCalculator implements QuantityCalculator {

  @Override
  public Map<String, Integer> calculateQuantity(Product product,
      KafkaReportedProduct reportedProduct, boolean isExistingOrder) {
    if (product.getReserved() == 0) {
      return Map.of("stock", product.getStock() - reportedProduct.getQuantity(),
          "reserved", reportedProduct.getQuantity());
    }
    if (isExistingOrder) {
      int productQuantityDifference =
          reportedProduct.getOldQuantity() - reportedProduct.getQuantity();
      if (productQuantityDifference < 0) {
        return Map.of("stock", product.getStock() - Math.abs(productQuantityDifference),
            "reserved", product.getReserved() + Math.abs(productQuantityDifference));
      } else {
        return Map.of("stock", product.getStock() + Math.abs(productQuantityDifference),
            "reserved", product.getReserved() - Math.abs(productQuantityDifference));
      }
    }
    return Map.of("stock", product.getStock() - reportedProduct.getQuantity(),
        "reserved", product.getReserved() + reportedProduct.getQuantity());
  }
}
