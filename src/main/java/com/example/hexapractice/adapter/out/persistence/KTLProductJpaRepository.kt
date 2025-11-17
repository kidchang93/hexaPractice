package com.example.hexapractice.adapter.out.persistence

import com.example.hexapractice.domain.KTLProduct
import org.springframework.data.jpa.repository.JpaRepository

/**
 * jpa 레포지토리의 제네릭 타입 첫번째는 Jpa entity 가 와야한다.
 */
interface KTLProductJpaRepository: JpaRepository<KTLProductJPAEntity, Long> {

    /**
     * 이름으로 제품 검색 (부분 일치)
     */
    fun findByNameContaining(name: String): List<KTLProductJPAEntity>

}