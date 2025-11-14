package com.example.hexapractice.domain

import java.math.BigDecimal

data class KTLProduct(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stock: Int
) {
    /**
     * 재고 확인
     */
    fun isAvailable(): Boolean {
        return stock > 0;
    }

    /**
     * 재고 감소
     */
    fun decreaseStock(quantity: Int): KTLProduct{
        validateQuantity(quantity);
        if (stock < quantity) throw IllegalArgumentException(String.format("재고 부족: 현재 =%d, 요청=%d", stock, quantity));
        return copy(stock = stock - quantity);
    }

    private fun validateQuantity(quantity: Int) {

    }
}