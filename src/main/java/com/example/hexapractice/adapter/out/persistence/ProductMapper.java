package com.example.hexapractice.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.example.hexapractice.domain.Product;

@Component
public class ProductMapper {
     /**
     * JPA Entity → Domain
     */
    public Product toDomain(ProductJpaEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }
    
    /**
     * Domain → JPA Entity
     */
    public ProductJpaEntity toEntity(Product product) {
        return ProductJpaEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
