package com.tul.inventory.domain.usecases;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import java.util.List;
import java.util.UUID;

public interface InventoryUseCase {

  UUID saveProduct(Product product);

  List<ProductControllerResponse> getInventory();

  void deleteProduct(UUID sku);

  ProductControllerResponse getProductBySku(UUID sku);

}
