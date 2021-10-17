package com.tul.inventory.infraestructure.entrypoint.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerRequest;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class InventoryControllerProcessProductTest extends InventoryControllerInitializer {

  @ParameterizedTest(name = "{index}: value = {0}")
  @MethodSource("processProductProvider")
  void shouldReturnBadRequestWhenAnyRequiredFieldIsMissing(ProductControllerRequest product)
      throws Exception {
    mockMvc.perform(put("/v1/api/inventories")
            .contentType(APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsBytes(product)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturnUUIDWhenProductIsSaved() throws Exception {
    ProductControllerRequest product = ProductControllerRequest.builder()
        .name("name")
        .description("description")
        .price(BigDecimal.valueOf(1.02))
        .stock(1)
        .build();
    UUID sku = UUID.randomUUID();
    Product convertedProduct = Product.builder()
        .name("name")
        .build();
    when(controllerMapper.toProduct(product, null)).thenReturn(convertedProduct);
    when(inventoryUseCase.saveProduct(convertedProduct)).thenReturn(sku);
    mockMvc.perform(put("/v1/api/inventories")
            .contentType(APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsBytes(product)))
        .andExpect(status().isOk())
        .andExpect(result -> result.getResponse().getContentAsString());
  }
}