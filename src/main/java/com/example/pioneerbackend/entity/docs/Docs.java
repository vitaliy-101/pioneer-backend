package com.example.pioneerbackend.entity.docs;

import com.example.pioneerbackend.dto.docs.DocType;
import com.example.pioneerbackend.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "docs")
@Getter
@Setter
public class Docs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private byte[] data;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DocType type;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
