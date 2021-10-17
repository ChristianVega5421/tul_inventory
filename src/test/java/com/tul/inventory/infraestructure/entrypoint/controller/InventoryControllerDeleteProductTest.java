package com.tul.inventory.infraestructure.entrypoint.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class InventoryControllerDeleteProductTest extends InventoryControllerInitializer {

  @SneakyThrows
  @Test
  void shouldCallDeleteMethodWhenTryingToDelete() {
    UUID sku = UUID.randomUUID();
    mockMvc.perform(delete(String.join("/", "/v1/api/inventories", sku.toString())))
        .andExpect(status().isOk());
    verify(inventoryUseCase, times(1)).deleteProduct(sku);

  }

}