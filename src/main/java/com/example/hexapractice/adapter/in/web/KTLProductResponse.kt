package com.example.hexapractice.adapter.`in`.web

import com.example.hexapractice.domain.KTLProduct
import java.math.BigDecimal

/**
 * 로직을 수행한 뒤 Response 반환하는 객체
 */
data class KTLProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stock: Int,
    val available: Boolean,
    val canBeSold: Boolean,
) {

    /**
     * 관례중 하나라고 하는데
     * 팩토리 메서드를 정적 메서드처럼 쓰려면 이렇게 해야 한다.
     */
    companion object {
        fun from(product: KTLProduct) =
            KTLProductResponse(
                id = product.id,
                name = product.name,
                description = product.description,
                price = product.price,
                stock = product.stock,
                available = product.isAvailable(),
                canBeSold = product.canBeSold(),
            )
    }
}