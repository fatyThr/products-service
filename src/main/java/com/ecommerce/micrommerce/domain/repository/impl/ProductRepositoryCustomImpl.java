package com.ecommerce.micrommerce.domain.repository.impl;

import com.ecommerce.micrommerce.application.beans.Pagination;
import com.ecommerce.micrommerce.domain.entities.Product;
import com.ecommerce.micrommerce.domain.repository.ProductRepositoryCustom;
import com.ecommerce.micrommerce.domain.service.command.ProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Pagination<Product> findAllProductsByFilter(ProductCommand productCommand, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder(" select p from Product p ");
        StringBuilder countBuilder = new StringBuilder(" select count(p) from Product p ");
        StringBuilder whereBuilder = new StringBuilder(" where 1=1");

        if (productCommand.getName() != null) {
            whereBuilder.append(" and p.name = :name ");
            params.put("name", productCommand.getName());
        }
        if (productCommand.getPrice() != null) {
            whereBuilder.append(" and p.price = :price ");
            params.put("price", productCommand.getPrice());
        }
        countBuilder.append(whereBuilder);
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        params.forEach(countQuery::setParameter);
        long count = (long) countQuery.getSingleResult();

        whereBuilder.append(" order by p.creationDate desc ");
        queryBuilder.append(whereBuilder);
        Query query = entityManager.createQuery(queryBuilder.toString());
        params.forEach(query::setParameter);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        int totalPages = (int) Math.ceil((double) count / pageSize);

        return new Pagination<>(query.getResultList(),pageNumber - 1,pageSize,count,totalPages);

    }
}
