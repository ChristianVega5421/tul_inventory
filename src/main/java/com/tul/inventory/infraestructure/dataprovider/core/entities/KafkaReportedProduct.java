package com.tul.inventory.infraestructure.dataprovider.core.entities;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class KafkaReportedProduct {

  UUID sku;
  int quantity;
  int oldQuantity;
  String status;
}
