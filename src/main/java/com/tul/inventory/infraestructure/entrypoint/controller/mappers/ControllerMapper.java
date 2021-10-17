package com.tul.inventory.infraestructure.entrypoint.controller.mappers;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerRequest;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ControllerMapper {

  @Mapping(target = "reserved", ignore = true)
  Product toProduct(ProductControllerRequest product, UUID sku);
}
