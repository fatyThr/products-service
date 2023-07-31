package com.ecommerce.micrommerce.application.controller;

import com.ecommerce.micrommerce.domain.service.representation.ProductRepresentation;
import com.ecommerce.micrommerce.infrastructure.exceptions.ProductException;
import com.ecommerce.micrommerce.domain.entities.Product;
import com.ecommerce.micrommerce.domain.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Api( "API for CRUD operations on products.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {
     private final ProductService productService;


    @DeleteMapping (value = "/products/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping (value = "/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        productService.saveOrUpdateProduct(product);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ApiOperation(value = "get all products")
    @GetMapping("/products")
    public List<ProductRepresentation> getProducts() {

        return productService.findAllProductOrderByName();
    }

    @ApiOperation(value = "get product with ID if exist!")
    @GetMapping(value = "/products/{id}")
    public ProductRepresentation getProductById(@PathVariable UUID id) {
        ProductRepresentation product = productService.getProductById(id);
        if(product==null) throw new ProductException("the product with Id" + id + " is not found.");
        return product;
    }


    @PostMapping(value = "/products")
    public ResponseEntity<ProductRepresentation> addProduct(@RequestBody @Valid Product product) {
      if(product.getPrice().compareTo(BigDecimal.ZERO)<=0) {throw new ProductException("the selling price must be greater then 0!");}
        ProductRepresentation productAdded = productService.saveOrUpdateProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "calculate the margin between sale price and purchase price ")
    @GetMapping(value = "/products/AdminProducts")
    public HashMap<ProductRepresentation,BigDecimal> calculateMargeProduct() {
         return productService.getMargeProducts();
    }

    @GetMapping(value= "/products/maxSize/{size}")
    public List<ProductRepresentation> generateListOfProducts(@PathVariable Long size) {

        return productService.handle(size);
    }



}