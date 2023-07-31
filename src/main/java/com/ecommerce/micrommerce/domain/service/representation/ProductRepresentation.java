package com.ecommerce.micrommerce.domain.service.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRepresentation {

    private UUID id;
    private String name;
    private BigDecimal price;
    private BigDecimal purchase;
}
