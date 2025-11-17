package com.example.hexapractice.adapter.out.persistence

import com.example.hexapractice.domain.KTLProduct
import org.springframework.stereotype.Component

/**
 * 도메인에는 무조건 id 가 들어올 테니 라는 가정이 있어야 한다.
 * 그러므로 Domain 쪽 엔티티는 최대한 non-null 상태를 유지해야한다.
 * 여기서는 JPA
 */
@Component
class KTLProductMapper {

    fun toDomain(entity: KTLProductJPAEntity) =
        KTLProduct(
            id = entity.id ?: throw IllegalArgumentException("저장된 제품에 ID가 없습니다."),
            name = entity.name,
            description = entity.description ?: throw IllegalArgumentException("제품에 대한 설명이 없습니다."),
            price = entity.price,
            stock = entity.stock,
        )
}