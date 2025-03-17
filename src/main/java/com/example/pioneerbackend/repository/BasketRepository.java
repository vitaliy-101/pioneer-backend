package com.example.pioneerbackend.repository;

import com.example.pioneerbackend.dto.basket.BasketElementInfo;
import com.example.pioneerbackend.entity.basket.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Modifying
    @Query("""
                UPDATE Basket b SET b.count = :count
                WHERE ((:userId IS NOT NULL AND b.user.id = :userId)
                   OR (:userId IS NULL AND b.uuid = :uuid)) AND b.product.id = :productId
            """)
    void updateBasketCount(@Param("count") Integer count,
                           @Param("userId") Long userId,
                           @Param("uuid") String uuid,
                           @Param("productId") Long productId);

    @Modifying
    @Query("""
                DELETE Basket b
                WHERE ((:userId IS NOT NULL AND b.user.id = :userId)
                   OR (:userId IS NULL AND b.uuid = :uuid)) AND b.product.id = :productId
            """)
    void deleteById(@Param("userId") Long userId,
                    @Param("uuid") String uuid,
                    @Param("productId") Long productId);


    @Query("""
                SELECT new com.example.pioneerbackend.dto.basket.BasketElementInfo(
                    b.id,
                    p.id,
                    b.count,
                    p.price,
                    p.title,
                    pi.id
                )
                FROM Basket b
                JOIN Product p ON b.product.id = p.id
                LEFT JOIN ProductImage pi ON pi.product.id = p.id AND pi.isMain = true
                WHERE (:userId IS NOT NULL AND b.user.id = :userId)
                   OR (:userId IS NULL AND b.uuid = :uuid)
                ORDER BY b.id
            """)
    List<BasketElementInfo> findBasketElements(@Param("userId") Long userId, @Param("uuid") String uuid);


    @Query("""
            SELECT b
            FROM Basket b
            WHERE ((:userId IS NOT NULL AND b.user.id = :userId)
               OR (:userId IS NULL AND b.uuid = :uuid))
               AND b.product.id IN :productIds
            ORDER BY b.id
        """)
    List<Basket> findBasketsByProducts(
            @Param("userId") Long userId,
            @Param("uuid") String uuid,
            @Param("productIds") List<Long> productIds
    );

    @Query("""
            SELECT b
            FROM Basket b
            WHERE ((:userId IS NOT NULL AND b.user.id = :userId)
               OR (:userId IS NULL AND b.uuid = :uuid))
               AND b.product.id = :productId
            ORDER BY b.id
        """)
    Basket findBasketsByProductId(
            @Param("userId") Long userId,
            @Param("uuid") String uuid,
            @Param("productId") Long productId
    );


}
