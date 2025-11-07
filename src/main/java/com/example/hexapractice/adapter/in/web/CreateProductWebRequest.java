package com.example.hexapractice.adapter.in.web;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Web 요청 DTO - API 계층
 * Command와 분리하여 API 변경에 유연하게 대응
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductWebRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
}
