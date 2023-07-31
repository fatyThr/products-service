package com.ecommerce.micrommerce.domain.repository;

import com.ecommerce.micrommerce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<Product, UUID> {

    @Query("select p from Product  p order by p.name")
    List<Product> findAllOrOrderByName();
}
