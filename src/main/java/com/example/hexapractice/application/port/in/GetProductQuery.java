package com.example.hexapractice.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 제품 단건 조회 쿼리
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductQuery {
    
    @NotNull(message = "제품 ID는 필수입니다.")
    private Long id;
}