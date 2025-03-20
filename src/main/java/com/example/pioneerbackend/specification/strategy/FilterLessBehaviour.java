package com.example.pioneerbackend.specification.strategy;

import com.example.pioneerbackend.specification.FilterUnit;

import static com.example.pioneerbackend.util.FilterUtils.verify;

public class FilterLessBehaviour implements FilterBehaviour {
    @Override
    public void addFilter(FilterUnit filterUnit) {
        if (verify(filterUnit)) {
            var key = filterUnit.getFilter().getKey();
            var value = Double.valueOf(filterUnit.getFilter().getValues().getFirst());
            filterUnit.getPredicates().add(
                    filterUnit.getCriteriaBuilder().lessThanOrEqualTo(filterUnit.getRoot().get(key), value)
            );
        }
    }

}
