package com.tul.inventory.infraestructure.dataprovider.entities;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "product", schema = "inventory")
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductJpaEntity {

  @Id
  @GeneratedValue(strategy = AUTO)
  UUID sku;
  String name;
  String description;
  BigDecimal price;
  int stock;
  int reserved;

}
