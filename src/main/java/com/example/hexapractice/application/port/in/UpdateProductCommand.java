package com.example.hexapractice.application.port.in;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 제품 수정 커맨드
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCommand {
    
    @NotNull(message = "제품 ID는 필수입니다.")
    private Long id;
    
    @Size(min = 1, max = 100, message = "제품명은 1~100자 이내여야 합니다.")
    private String name;
    
    @Size(max = 500, message = "설명은 500자 이내여야 합니다.")
    private String description;
    
    @DecimalMin(value = "0.0", inclusive = true, message = "가격은 0 이상이어야 합니다.")
    private BigDecimal price;
    
    @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    private Integer stock;
}