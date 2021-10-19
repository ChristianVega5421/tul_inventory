package com.tul.inventory.infraestructure.dataprovider;

import com.tul.inventory.infraestructure.dataprovider.core.KafkaProducer;
import com.tul.inventory.infraestructure.dataprovider.core.mapper.KafkaMapper;
import com.tul.inventory.infraestructure.dataprovider.jpa.InventoryProvider;
import com.tul.inventory.infraestructure.dataprovider.jpa.mappers.JpaMapper;
import com.tul.inventory.infraestructure.dataprovider.jpa.repositories.ProductRepository;
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
  @Mock
  KafkaMapper kafkaMapper;
  @Mock
  KafkaProducer kafkaProducer;

}
