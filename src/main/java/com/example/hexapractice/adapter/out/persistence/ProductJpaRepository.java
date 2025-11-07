package com.example.hexapractice.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository
 */
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
    /**
     * 이름으로 제품 검색 (부분 일치)
     */
    List<ProductJpaEntity> findByNameContaining(String name);
}
