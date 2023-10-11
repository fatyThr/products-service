package com.ecommerce.micrommerce.domain.service.impl;

import com.ecommerce.micrommerce.application.beans.Pagination;
import com.ecommerce.micrommerce.domain.entities.ImageRandomResponse;
import com.ecommerce.micrommerce.domain.entities.Product;
import com.ecommerce.micrommerce.domain.repository.ProductRepository;
import com.ecommerce.micrommerce.domain.service.ProductService;
import com.ecommerce.micrommerce.domain.service.command.ProductCommand;
import com.ecommerce.micrommerce.domain.service.impl.mapper.ProductMapper;
import com.ecommerce.micrommerce.domain.service.representation.ProductRepresentation;
import com.ecommerce.micrommerce.infrastructure.exceptions.ProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private  final ProductMapper productMapper;

    @Override
    public void deleteProduct(Long id) {
        log.info("Delete product with id {} ",id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductRepresentation saveOrUpdateProduct(ProductCommand productCommand) {
        log.info("save product {} ", productCommand);
        Product product= Product.
                builder().
                name(productCommand.getName())
                .price(productCommand.getPrice())
                .purchase(productCommand.getPurchase())
                .build();
        return productMapper.productToProductRepresentation(productRepository.save(product));
    }


    @Override
    public List<ProductRepresentation> findAllProductOrderByName() {
        return productRepository.findAllOrOrderByName().stream().map(productMapper::productToProductRepresentation).collect(Collectors.toList());
    }

    @Override
    public ProductRepresentation getProductById(Long id) {
        return productMapper.productToProductRepresentation(productRepository.findById(id).orElseThrow(() -> new ProductException(" The product with ID :  "+id+" not found!")));
    }

    @Override
    public HashMap<ProductRepresentation, BigDecimal> getMargeProducts() {
        HashMap<ProductRepresentation,BigDecimal> map=new HashMap<>();
        productRepository.findAll().stream().map(productMapper::productToProductRepresentation).collect(Collectors.toList()).forEach(product ->
                map.put(product,product.getPrice().subtract(product.getPurchase()))
        );
        return map;
    }


    @Override
    public List<ProductRepresentation> handle(Long size) {

        List<Product> products = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ImageRandomResponse response = restTemplate.getForEntity("https://dog.ceo/api/breeds/image/random", ImageRandomResponse.class).getBody();
        if(response!=null && response.getImage()!=null){
        Stream.iterate(1, p -> p + 1)
                .limit(size)
                .forEach(n -> products.add(Product.builder()
                        .name("Product N "+n)
                        .price(BigDecimal.valueOf(400).add(new BigDecimal(n)))
                        .purchase(BigDecimal.valueOf(100).add(new BigDecimal(n)))
                        .creationDate(LocalDateTime.now())
                        .imagePath(response.getImage())
                        .build()));
        }
        productRepository.saveAll(products);
        return products.stream().map(productMapper::productToProductRepresentation).collect(Collectors.toList());
    }

    @Override
    public Pagination<ProductRepresentation> search(ProductCommand productCommand, Pageable pageable) {
        Pagination<Product> pagesProducts= productRepository.findAllProductsByFilter(productCommand,pageable);
        List<ProductRepresentation> productRepresentations=pagesProducts.getContent().stream().map(productMapper::productToProductRepresentation).collect(Collectors.toList());
        return new Pagination<>(productRepresentations, pagesProducts.getPage(), pagesProducts.getSize(),pagesProducts.getTotalElements() ,pagesProducts.getTotalPages());
    }

}
