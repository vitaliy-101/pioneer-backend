package com.example.pioneerbackend.repository;

import com.example.pioneerbackend.entity.filter.FilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<FilterEntity, Long> {
}
