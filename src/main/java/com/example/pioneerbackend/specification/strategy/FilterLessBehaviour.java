package com.example.pioneerbackend.specification.strategy;

import com.example.pioneerbackend.specification.FilterUnit;

public class FilterLessBehaviour implements FilterBehaviour {
    @Override
    public void addFilter(FilterUnit filterUnit) {
        if (verify(filterUnit)) {
            var type = filterUnit.getFilter().getType();
            var key = filterUnit.getFilter().getKey();
            var value = Double.valueOf(filterUnit.getFilter().getValues().getFirst());
            filterUnit.getPredicates().add(
                    filterUnit.getCriteriaBuilder().lessThanOrEqualTo(filterUnit.getRoot().get(key), value)
            );
        }
    }

    private boolean verify(FilterUnit filterUnit) {
        var values = filterUnit.getFilter().getValues();
        return values != null && !values.isEmpty();
    }
}
