package com.tul.inventory.infraestructure.dataprovider.core.mapper;

import com.tul.inventory.domain.entities.Product;
import java.util.Map;
import java.util.UUID;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class KafkaMapper {

  public abstract Product toUpdatedProduct(Product product, Map<String, Integer> quantities);


  @AfterMapping
  protected Product updateQuantities(@MappingTarget Product.ProductBuilder product,
      Map<String, Integer> quantities) {
    product.stock(quantities.get("stock"));
    product.reserved(quantities.get("reserved"));
    return product.build();
  }

  @Mapping(target = "sku", source = "sku")
  public abstract Product toMessageProduct(Product product, UUID sku);
}
