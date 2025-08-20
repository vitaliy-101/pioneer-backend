package com.example.pioneerbackend.repository;

import com.example.pioneerbackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Modifying
    @Query("""
                 UPDATE Product p
                 SET p.sales = p.sales + :sales
                 WHERE p.id = :id
            """)
    void updateSale(@Param("sales") Integer sales, @Param("id") Long id);
}
