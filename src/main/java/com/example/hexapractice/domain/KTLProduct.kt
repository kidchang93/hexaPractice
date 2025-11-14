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

    /**
     * 재고 증가
     */
    fun increaseStock(quantity: Int): KTLProduct{
        validateQuantity(quantity);
        return copy(stock = stock + quantity);
    }

    /**
     * 가격 변경
     */
    fun changPrice(newPrice: BigDecimal): KTLProduct {
        if (newPrice <= BigDecimal.ZERO){
            throw IllegalArgumentException()
        }
        return copy(price = newPrice);
    }

    /**
     * 판매 가능 여부
     */
    fun canBeSold(): Boolean {
        return isAvailable() && price > BigDecimal.ZERO
    }

    private fun validateQuantity(quantity: Int) {
        if (quantity <= 0){
            throw IllegalArgumentException("수량은 0보다 커야 합니다.")
        }
    }
}