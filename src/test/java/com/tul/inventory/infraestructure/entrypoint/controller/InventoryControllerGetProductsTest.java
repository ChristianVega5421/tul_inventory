package com.tul.inventory.infraestructure.entrypoint.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

class InventoryControllerGetProductsTest extends InventoryControllerInitializer {


  @Test
  void shouldRespondWithBadRequestWhenRequiredFieldIsMissing() throws Exception {
    List<ProductControllerResponse> serviceResponse = List.of(ProductControllerResponse.builder()
        .description("description")
        .build());
    when(inventoryUseCase.getInventory()).thenReturn(serviceResponse);
    mockMvc.perform(get("/v1/api/inventories"))
        .andExpect(status().isOk())
        .andExpect(result -> assertEquals(objectMapper.writeValueAsString(serviceResponse),
            result.getResponse().getContentAsString()));
  }
}