package com.ecommerce.micrommerce.domain.repository;

import com.ecommerce.micrommerce.application.beans.Pagination;
import com.ecommerce.micrommerce.domain.entities.Product;
import com.ecommerce.micrommerce.domain.service.command.ProductCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    public Pagination<Product> findAllProductsByFilter(ProductCommand productCommand, Pageable pageable);
}
