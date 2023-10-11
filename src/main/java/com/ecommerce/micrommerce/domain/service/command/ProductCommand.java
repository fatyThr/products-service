package com.ecommerce.micrommerce.domain.service.command;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ProductCommand extends  ElementCommand{

    private String name;
    private BigDecimal price;
    private BigDecimal purchase;
}
