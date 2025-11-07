package com.example.hexapractice.adapter.in.web;

import com.example.hexapractice.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Web 응답 DTO
 */
@Getter
@Builder
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private boolean available;
    private boolean canBeSold;
    
    /**
     * Domain → Response 변환
     */
    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .available(product.isAvailable())
                .canBeSold(product.canBeSold())
                .build();
    }
}