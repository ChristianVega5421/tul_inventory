package com.tul.inventory.infraestructure.dataprovider.core.utils;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.core.entities.KafkaReportedProduct;
import java.util.Map;

public interface QuantityCalculator {

  Map<String, Integer> calculateQuantity(Product product, KafkaReportedProduct reportedProduct,
      boolean isExistingOrder);

}
