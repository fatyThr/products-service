package com.ecommerce.micrommerce.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private UUID id;
    @Size(min = 3, max = 25)
    private String name;
    @Min(value = 1)
    private BigDecimal price;
    private BigDecimal purchase;
    LocalDateTime creationDate;
    String imagePath="/";

    @PrePersist
    protected void onPrePersist() {
        if(this.getId() == null){
            this.setId(UUID.randomUUID());
        }
        if(this.getCreationDate() == null){
            this.setCreationDate(LocalDateTime.now());
        }
     }


}
