package com.example.hexapractice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 도메인 모델 - 순수한 비즈니스 엔티티
 * 외부 기술(JPA, Spring 등)에 의존하지 않음
 */
@Getter
@Builder
@AllArgsConstructor
public class Product {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    
    // === 도메인 비즈니스 로직 ===
    
    /**
     * 재고 확인
     */
    public boolean isAvailable() {
        return stock != null && stock > 0;
    }
    
    /**
     * 재고 감소
     */
    public void decreaseStock(int quantity) {
        validateQuantity(quantity);
        if (stock < quantity) {
            throw new IllegalStateException(
                String.format("재고 부족: 현재=%d, 요청=%d", stock, quantity)
            );
        }
        this.stock -= quantity;
    }
    
    /**
     * 재고 증가
     */
    public void increaseStock(int quantity) {
        validateQuantity(quantity);
        this.stock += quantity;
    }
    
    /**
     * 가격 변경
     */
    public Product changePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(newPrice)
                .stock(this.stock)
                .build();
    }
    
    /**
     * 판매 가능 여부
     */
    public boolean canBeSold() {
        return isAvailable() 
            && price != null 
            && price.compareTo(BigDecimal.ZERO) > 0;
    }
    
    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
        }
    }
}