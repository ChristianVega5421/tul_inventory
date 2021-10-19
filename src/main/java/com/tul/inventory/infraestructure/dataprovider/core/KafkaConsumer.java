package com.tul.inventory.infraestructure.dataprovider.core;

import static com.tul.inventory.infraestructure.dataprovider.jpa.entities.ProductStatusEnum.RESERVED;
import static com.tul.inventory.infraestructure.dataprovider.jpa.entities.ProductStatusEnum.SOLD;
import static com.tul.inventory.infraestructure.dataprovider.jpa.entities.ProductStatusEnum.STOCK;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.core.entities.KafkaCart;
import com.tul.inventory.infraestructure.dataprovider.core.entities.KafkaReportedProduct;
import com.tul.inventory.infraestructure.dataprovider.core.mapper.KafkaMapper;
import com.tul.inventory.infraestructure.dataprovider.core.utils.DeletedProductCalculator;
import com.tul.inventory.infraestructure.dataprovider.core.utils.QuantityCalculator;
import com.tul.inventory.infraestructure.dataprovider.core.utils.ReservedProductCalculator;
import com.tul.inventory.infraestructure.dataprovider.core.utils.SoldProductCalculator;
import com.tul.inventory.infraestructure.dataprovider.jpa.InventoryProvider;
import java.util.Map;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {

  InventoryProvider inventoryProvider;
  KafkaMapper kafkaMapper;

  public KafkaConsumer(InventoryProvider inventoryProvider, KafkaMapper kafkaMapper) {
    this.inventoryProvider = inventoryProvider;
    this.kafkaMapper = kafkaMapper;
  }

  @SneakyThrows
  @KafkaListener(topics = "${kafka.cart.product.topic}")
  public void reportedCartProducts(String stringProducts) {
    KafkaCart products = new ObjectMapper().readValue(stringProducts,
        KafkaCart.class);
    products.getCartProducts().forEach(reportedProduct -> {
      Product product = inventoryProvider.getProductBySku(reportedProduct.getSku());
      inventoryProvider.saveProduct(kafkaMapper.toUpdatedProduct(product,
          calculateQuantity(product, reportedProduct, products.isExistingOrder())));
    });
  }

  private Map<String, Integer> calculateQuantity(Product product,
      KafkaReportedProduct reportedProduct, boolean isExistingOrder) {
    Map<String, QuantityCalculator> quantityCalculator = Map
        .of(RESERVED.name(), new ReservedProductCalculator(),
            SOLD.name(), new SoldProductCalculator(),
            STOCK.name(), new DeletedProductCalculator());
    return quantityCalculator.get(reportedProduct.getStatus()).calculateQuantity(product,
        reportedProduct, isExistingOrder);
  }
}
