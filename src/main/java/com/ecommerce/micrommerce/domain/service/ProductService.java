package com.ecommerce.micrommerce.domain.service;

import com.ecommerce.micrommerce.application.beans.Pagination;
import com.ecommerce.micrommerce.domain.service.command.ProductCommand;
import com.ecommerce.micrommerce.domain.service.representation.ProductRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface ProductService {

    public void deleteProduct(Long id);

    public ProductRepresentation saveOrUpdateProduct(ProductCommand productCommand);

    List<ProductRepresentation> findAllProductOrderByName();

    ProductRepresentation getProductById(Long id);

    public HashMap<ProductRepresentation, BigDecimal> getMargeProducts();

    public List<ProductRepresentation> handle(Long size);

    Pagination<ProductRepresentation> search(ProductCommand productCommand, Pageable pageable);
}
