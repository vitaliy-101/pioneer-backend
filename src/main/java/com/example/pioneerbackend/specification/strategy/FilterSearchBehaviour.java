package com.example.pioneerbackend.specification.strategy;

import com.example.pioneerbackend.specification.FilterUnit;

import static com.example.pioneerbackend.util.FilterUtils.verify;

public class FilterSearchBehaviour implements FilterBehaviour {
    @Override
    public void addFilter(FilterUnit filterUnit) {
        if (verify(filterUnit)) {
            var key = filterUnit.getFilter().getKey();
            var value = filterUnit.getFilter().getValues().getFirst();
            var likePattern = "%" + value.toLowerCase() + "%";
            filterUnit.getPredicates().add(
                    filterUnit.getCriteriaBuilder().like(
                            filterUnit.getCriteriaBuilder().lower(
                                    filterUnit.getRoot().get(key)
                            ),
                            likePattern
                    )
            );
        }
    }
}
