package com.ecommerce.micrommerce.domain.service.mapper;

import com.ecommerce.micrommerce.domain.entities.Product;
import com.ecommerce.micrommerce.domain.service.representation.ProductRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductRepresentation productToProductRepresentation(Product product);



}
