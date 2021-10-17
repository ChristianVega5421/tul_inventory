package com.tul.inventory.domain.services;

import static lombok.AccessLevel.PRIVATE;

import com.tul.inventory.common.LogInformation;
import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.domain.mappers.ServiceMapper;
import com.tul.inventory.domain.usecases.InventoryUseCase;
import com.tul.inventory.domain.usecases.ports.InventoryPort;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import java.util.List;
import java.util.UUID;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@FieldDefaults(level = PRIVATE)
@Service
@Log4j2
public class InventoryService implements InventoryUseCase {

  final InventoryPort inventoryPort;
  final ServiceMapper serviceMapper;

  @Autowired
  public InventoryService(InventoryPort inventoryPort, ServiceMapper serviceMapper) {
    this.inventoryPort = inventoryPort;
    this.serviceMapper = serviceMapper;
  }

  @Override
  public UUID saveProduct(Product product) {
    StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message("processing save product")
        .level(2)
        .build());
    UUID savedProductSKU = inventoryPort.saveProduct(product);
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message(String.join(" ", "processed save product:", savedProductSKU
            .toString()))
        .level(2)
        .build());
    return savedProductSKU;
  }

  @Override
  public List<ProductControllerResponse> getInventory() {
    StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message("processing get product")
        .level(2)
        .build());
    List<ProductControllerResponse> inventory = serviceMapper
        .toProductControllerResponseList(inventoryPort.getInventory());
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message("processed get product")
        .level(2)
        .build());
    return inventory;
  }

  @Override
  public void deleteProduct(UUID sku) {
    StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message("processing delete product")
        .level(2)
        .build());
    inventoryPort.deleteProduct(sku);
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message("processed delete product")
        .level(2)
        .build());
  }

  @Override
  public ProductControllerResponse getProductBySku(UUID sku) {
    StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message("received get product by sku")
        .level(2)
        .build());
    ProductControllerResponse product = serviceMapper.toProductControllerResponse(inventoryPort
        .getProductBySku(sku));
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .message("processed get product by sku")
        .level(2)
        .build());
    return product;
  }
}
