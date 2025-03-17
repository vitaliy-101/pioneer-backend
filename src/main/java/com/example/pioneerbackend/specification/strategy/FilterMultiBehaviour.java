package com.example.pioneerbackend.specification.strategy;

import com.example.pioneerbackend.specification.FilterUnit;

import static com.example.pioneerbackend.util.FilterUtils.convertTypeValue;

public class FilterMultiBehaviour implements FilterBehaviour {
    @Override
    public void addFilter(FilterUnit filterUnit) {
        if (verify(filterUnit)) {
            var type = filterUnit.getFilter().getType();
            var key = filterUnit.getFilter().getKey();
            var values = filterUnit.getFilter().getValues().stream()
                    .map(value -> convertTypeValue(value, type));
            filterUnit.getPredicates().add(filterUnit.getRoot().get(key).in(values));
        }
    }

    private boolean verify(FilterUnit filterUnit) {
        var values = filterUnit.getFilter().getValues();
        return values != null && !values.isEmpty();
    }
}
