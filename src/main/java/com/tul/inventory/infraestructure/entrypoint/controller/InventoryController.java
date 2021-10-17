package com.tul.inventory.infraestructure.entrypoint.controller;

import static lombok.AccessLevel.PRIVATE;

import com.tul.inventory.common.LogInformation;
import com.tul.inventory.domain.usecases.InventoryUseCase;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerRequest;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import com.tul.inventory.infraestructure.entrypoint.controller.mappers.ControllerMapper;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.path}/inventories")
@FieldDefaults(level = PRIVATE)
@Log4j2
public class InventoryController {

  final ControllerMapper controllerMapper;
  final InventoryUseCase inventoryUseCase;


  @Autowired
  public InventoryController(ControllerMapper controllerMapper, InventoryUseCase inventoryUseCase) {
    this.controllerMapper = controllerMapper;
    this.inventoryUseCase = inventoryUseCase;
  }

  @GetMapping
  public List<ProductControllerResponse> getProducts() {
    StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .service(traceElement.getClassName())
        .message("get product received")
        .level(1)
        .build());
    return inventoryUseCase.getInventory();
  }

  @GetMapping("{sku}")
  public ProductControllerResponse getProductBySku(@PathVariable UUID sku) {
    return inventoryUseCase.getProductBySku(sku);
  }

  @PutMapping
  public UUID processProduct(@RequestBody @Valid ProductControllerRequest product,
      @RequestParam(required = false) UUID sku) {
    StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .service(traceElement.getClassName())
        .message("process product received")
        .level(1)
        .build());
    return inventoryUseCase.saveProduct(controllerMapper.toProduct(product, sku));
  }

  @DeleteMapping("{sku}")
  public void deleteProduct(@PathVariable UUID sku) {
    StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
    log.info(LogInformation.builder()
        .method(traceElement.getMethodName())
        .service(traceElement.getClassName())
        .message("delete product received")
        .level(1)
        .build());
    inventoryUseCase.deleteProduct(sku);
  }
}
