package com.tul.inventory.infraestructure.entrypoint.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class InventoryControllerGetProductBySkuTest extends InventoryControllerInitializer {

  @Test
  void shouldReturnProductWhenTryingToGetProductBySku() throws Exception {
    UUID sku = UUID.randomUUID();
    ProductControllerResponse product = ProductControllerResponse.builder()
        .description("descrption")
        .build();
    when(inventoryUseCase.getProductBySku(sku)).thenReturn(product);
    mockMvc.perform(get(String.join("/", "/v1/api/inventories", sku.toString())))
        .andExpect(status().isOk())
        .andExpect(result -> {
          ProductControllerResponse response = objectMapper.readValue(result.getResponse()
              .getContentAsString(), ProductControllerResponse.class);
          assertThat(response).usingRecursiveComparison().isEqualTo(product);
        });
  }
}