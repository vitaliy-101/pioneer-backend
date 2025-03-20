package com.example.pioneerbackend.service.filter;

import com.example.pioneerbackend.dto.filter.Filter;
import com.example.pioneerbackend.entity.filter.FilterEntity;
import com.example.pioneerbackend.repository.FilterRepository;
import com.example.pioneerbackend.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final FilterRepository repository;

    public void create(List<Filter> filters) {
        var entity = new FilterEntity();
        entity.setId(1L);
        entity.setData(filters.stream()
                .map(JsonUtils::convertToString)
                .toList());
        repository.save(entity);
    }

    public List<Filter> get() {
        var filter = repository.getReferenceById(1L);
        return filter.getData().stream()
                .map(data -> JsonUtils.convertFromString(data, Filter.class))
                .toList();

    }

}
