package com.example.pioneerbackend.specification;

import com.example.pioneerbackend.dto.product.filter.Filter;
import com.example.pioneerbackend.entity.product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FilterUnit {
    private Filter filter;
    private List<Predicate> predicates;
    private CriteriaBuilder criteriaBuilder;
    private Root<Product> root;
}
