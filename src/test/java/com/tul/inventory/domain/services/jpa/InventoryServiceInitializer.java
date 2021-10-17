package com.tul.inventory.domain.services.jpa;

import com.tul.inventory.domain.mappers.ServiceMapper;
import com.tul.inventory.domain.services.InventoryService;
import com.tul.inventory.domain.usecases.ports.InventoryPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceInitializer {

  @Mock
  InventoryPort inventoryPort;
  @InjectMocks
  InventoryService inventoryService;
  @Mock
  ServiceMapper serviceMapper;

}
