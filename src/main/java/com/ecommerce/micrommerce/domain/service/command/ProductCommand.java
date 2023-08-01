package com.ecommerce.micrommerce.domain.service.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommand {

    private String name;
    private BigDecimal price;
    private BigDecimal purchase;
}
