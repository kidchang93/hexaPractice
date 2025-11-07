package com.example.hexapractice.application.port.in;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    public void validate() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("제품명은 필수입니다.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("재고는 0 이상이어야 합니다.");
        }
    }
}
