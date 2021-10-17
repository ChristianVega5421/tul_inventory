package com.tul.inventory.infraestructure.dataprovider;

import com.tul.inventory.infraestructure.dataprovider.mappers.JpaMapper;
import com.tul.inventory.infraestructure.dataprovider.repositories.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InventoryProviderInitializer {

  @InjectMocks
  InventoryProvider inventoryProvider;
  @Mock
  ProductRepository productRepository;
  @Mock
  JpaMapper jpaMapper;

}
