package com.example.pioneerbackend.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_image")
@Getter
@Setter
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "is_main")
    private boolean isMain = false;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}