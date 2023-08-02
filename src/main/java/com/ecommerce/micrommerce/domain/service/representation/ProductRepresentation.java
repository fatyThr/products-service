package com.ecommerce.micrommerce.domain.service.representation;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;


@Data
public class ProductRepresentation {

    private UUID id;
    private String name;
    private BigDecimal price;
    private BigDecimal purchase;
}
