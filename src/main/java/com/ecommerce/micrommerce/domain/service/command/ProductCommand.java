package com.ecommerce.micrommerce.domain.service.command;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductCommand {

    private String name;
    private BigDecimal price;
    private BigDecimal purchase;
}
