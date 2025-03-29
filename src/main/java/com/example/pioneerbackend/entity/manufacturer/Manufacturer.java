package com.example.pioneerbackend.entity.manufacturer;

import com.example.pioneerbackend.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "manufacturer")
@Getter
@Setter
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;

}
