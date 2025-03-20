package com.example.pioneerbackend.specification.strategy;

import com.example.pioneerbackend.specification.FilterUnit;

import static com.example.pioneerbackend.util.FilterUtils.*;

public class FilterSingleBehaviour implements FilterBehaviour {

    @Override
    public void addFilter(FilterUnit filterUnit) {
        if (verify(filterUnit)) {
            var type = filterUnit.getFilter().getType();
            var key = filterUnit.getFilter().getKey();
            var value = convertTypeValue(filterUnit.getFilter().getValues().getFirst(), type);
            filterUnit.getPredicates().add(
                    filterUnit.getCriteriaBuilder().equal(filterUnit.getRoot().get(key), value)
            );
        }
    }

}
