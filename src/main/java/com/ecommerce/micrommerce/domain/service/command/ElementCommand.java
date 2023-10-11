package com.ecommerce.micrommerce.domain.service.command;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@SuperBuilder(builderMethodName = "elementCommandBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ElementCommand implements Serializable {

    private Integer page;
    private Integer size;
    private String defaultSort;
    private Boolean defaultOrder;
}
