package com.example.pioneerbackend.specification;

import com.example.pioneerbackend.dto.product.filter.Filter;
import com.example.pioneerbackend.dto.product.filter.ProductFilter;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.specification.strategy.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductFilterSpecification {
    private final static Map<String, FilterBehaviour> FILTER_BEHAVIOUR_MAP = Map.of(
            "single", new FilterSingleBehaviour(),
            "multi", new FilterMultiBehaviour(),
            "more", new FilterMoreBehaviour(),
            "less", new FilterLessBehaviour()
    );

    public Specification<Product> filterByCriteria(ProductFilter request) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();
            if (request.getFilters() != null) {
                request.getFilters()
                        .forEach(filter -> addFilter(filter, predicates, criteriaBuilder, root));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private FilterBehaviour selectBehaviour(String filterType) {
        if (!FILTER_BEHAVIOUR_MAP.containsKey(filterType)) {
            throw new RuntimeException("Filter behaviour by filter type does not exists");
        }
        return FILTER_BEHAVIOUR_MAP.get(filterType);
    }

    private void addFilter(Filter filter,
                           List<Predicate> predicates,
                           CriteriaBuilder criteriaBuilder,
                           Root<Product> root) {
        selectBehaviour(filter.getFilterType())
                .addFilter(new FilterUnit(filter, predicates, criteriaBuilder, root));
    }
}
