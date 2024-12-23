package com.tul.inventory.infraestructure.dataprovider.jpa.mappers;

import com.tul.inventory.domain.entities.Product;
import com.tul.inventory.infraestructure.dataprovider.jpa.entities.ProductJpaEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaMapper {

  ProductJpaEntity toProductJpaEntity(Product product);

  List<Product> toProducts(List<ProductJpaEntity> databaseProducts);

  Product toProduct(ProductJpaEntity product);

}
