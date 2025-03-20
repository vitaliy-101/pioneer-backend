package com.example.pioneerbackend.entity.manufacturer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "manufacturer")
@Getter
@Setter
public class Manufacturer {
    @Id
    private String name;

    @Column(name = "image")
    private byte[] image;

}
