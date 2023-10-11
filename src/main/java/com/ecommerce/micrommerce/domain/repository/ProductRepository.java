package com.ecommerce.micrommerce.domain.repository;

import com.ecommerce.micrommerce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    @Query("select p from Product  p order by p.name")
    List<Product> findAllOrOrderByName();
}
