package com.example.hexapractice.application.port.in;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 제품 생성 커맨드
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {
    
    @NotBlank(message = "제품명은 필수입니다.")
    @Size(min = 1, max = 100, message = "제품명은 1~100자 이내여야 합니다.")
    private String name;
    
    @Size(max = 500, message = "설명은 500자 이내여야 합니다.")
    private String description;
    
    @NotNull(message = "가격은 필수입니다.")
    @DecimalMin(value = "0.0", inclusive = true, message = "가격은 0 이상이어야 합니다.")
    private BigDecimal price;
    
    @NotNull(message = "재고는 필수입니다.")
    @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    private Integer stock;
}