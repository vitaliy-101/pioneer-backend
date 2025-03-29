package com.example.pioneerbackend.specification.strategy;

import com.example.pioneerbackend.entity.manufacturer.Manufacturer;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.specification.FilterUnit;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

import static com.example.pioneerbackend.util.FilterUtils.convertTypeValue;
import static com.example.pioneerbackend.util.FilterUtils.verify;

public class FilterMultiBehaviour implements FilterBehaviour {
    @Override
    public void addFilter(FilterUnit filterUnit) {
        if (verify(filterUnit)) {
            var type = filterUnit.getFilter().getType();
            var key = filterUnit.getFilter().getKey();
            var values = filterUnit.getFilter().getValues().stream()
                    .map(value -> convertTypeValue(value, type))
                    .toList();
            //todo proxy/decorator
            if (key.contains(".")) {
                String[] parts = key.split("\\.");
                String associationName = parts[0];
                String fieldName = parts[1];

                Join<Product, Manufacturer> manufacturerJoin = filterUnit.getRoot()
                        .join(associationName, JoinType.LEFT);

                filterUnit.getPredicates().add(
                        manufacturerJoin.get(fieldName).in(values)
                );
            } else {
                filterUnit.getPredicates().add(
                        filterUnit.getRoot().get(key).in(values)
                );
            }
        }
    }

}
