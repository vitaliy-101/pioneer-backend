package com.example.pioneerbackend.entity.basket;

import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "basket")
@Getter
@Setter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    private Integer count;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "uuid")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
