package com.applyjob.myshop.repository;

import com.applyjob.myshop.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findByActiveTrue(Pageable pageable);

    List<Product> findByActiveTrue();

    Optional<Product> findByIdAndActiveTrue(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select p
            from Product p
            where p.id = :id
            and p.active = true
            """)
    Optional<Product> findByIdForUpdate(
            @Param("id") String id
    );
}
