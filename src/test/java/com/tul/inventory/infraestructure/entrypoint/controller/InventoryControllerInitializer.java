package com.tul.inventory.infraestructure.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tul.inventory.domain.usecases.InventoryUseCase;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerRequest;
import com.tul.inventory.infraestructure.entrypoint.controller.mappers.ControllerMapper;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerInitializer {

  @MockBean
  ControllerMapper controllerMapper;
  @MockBean
  InventoryUseCase inventoryUseCase;
  @Autowired
  MockMvc mockMvc;
  ObjectMapper objectMapper = new ObjectMapper();

  private static Stream<Arguments> processProductProvider() {
    return Stream.of(
        Arguments.of(ProductControllerRequest.builder()
            .build()),
        Arguments.of(ProductControllerRequest.builder()
            .name("name")
            .build()),
        Arguments.of(ProductControllerRequest.builder()
            .name("name")
            .description("description")
            .build()),
        Arguments.of(ProductControllerRequest.builder()
            .name("name")
            .description("description")
            .price(BigDecimal.valueOf(1.002))
            .build()),
        Arguments.of(ProductControllerRequest.builder()
            .name("name")
            .description("description")
            .price(BigDecimal.valueOf(123456789100.002))
            .build()),
        Arguments.of(ProductControllerRequest.builder()
            .name("name")
            .description("description")
            .price(BigDecimal.valueOf(1.02))
            .stock(0)
            .build()));
  }
}
