package com.example.pioneerbackend.entity.filter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "filter")
@Getter
@Setter
public class FilterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "data", columnDefinition = "TEXT[]")
    private List<String> data;

}
