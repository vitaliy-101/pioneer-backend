package com.example.pioneerbackend.repository;

import com.example.pioneerbackend.dto.docs.DocType;
import com.example.pioneerbackend.entity.docs.Docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocsRepository extends JpaRepository<Docs, Long> {
    List<Docs> findByType(DocType type);

    @Query("""
            SELECT d
            FROM Docs d
            WHERE d.product.id = :productId
            ORDER BY d.id
        """)
    List<Docs> findByProductId(Long productId);
}
