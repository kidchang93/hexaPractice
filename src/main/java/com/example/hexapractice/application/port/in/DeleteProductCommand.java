package com.example.hexapractice.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 제품 삭제 커맨드
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductCommand {
    
    @NotNull(message = "제품 ID는 필수입니다.")
    private Long id;
}