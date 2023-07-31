package com.ecommerce.micrommerce.domain.service;

import com.ecommerce.micrommerce.domain.entities.Product;
import com.ecommerce.micrommerce.domain.service.representation.ProductRepresentation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    public void deleteProduct(UUID id);

    public ProductRepresentation saveOrUpdateProduct(Product product);

    List<ProductRepresentation> findAllProductOrderByName();

    ProductRepresentation getProductById(UUID id);

    public HashMap<ProductRepresentation, BigDecimal> getMargeProducts();

    public List<ProductRepresentation> handle(Long size);
}
