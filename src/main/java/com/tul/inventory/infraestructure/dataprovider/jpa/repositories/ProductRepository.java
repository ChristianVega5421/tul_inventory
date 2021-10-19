package com.tul.inventory.infraestructure.dataprovider.jpa.repositories;

import com.tul.inventory.infraestructure.dataprovider.jpa.entities.ProductJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, UUID> {

}
