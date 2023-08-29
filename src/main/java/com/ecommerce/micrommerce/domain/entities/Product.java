package com.ecommerce.micrommerce.domain.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 25)
    private String name;
    @Min(value = 1)
    private BigDecimal price;
    private BigDecimal purchase;
    LocalDateTime creationDate;
    String imagePath="/";

    @PrePersist
    protected void onPrePersist() {

        if(this.getCreationDate() == null){
            this.setCreationDate(LocalDateTime.now());
        }
     }


}
