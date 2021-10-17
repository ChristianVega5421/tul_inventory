package com.tul.inventory.domain.usecases.ports;

import com.tul.inventory.domain.entities.Product;
import java.util.List;
import java.util.UUID;

public interface InventoryPort {

  UUID saveProduct(Product product);

  List<Product> getInventory();

  void deleteProduct(UUID sku);

  Product getProductBySku(UUID sku);
}
