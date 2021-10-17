package com.tul.inventory.domain.mappers;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.entrypoint.controller.entities.ProductControllerResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

  List<ProductControllerResponse> toProductControllerResponseList(List<Product> products);

  ProductControllerResponse toProductControllerResponse(Product product);

}
