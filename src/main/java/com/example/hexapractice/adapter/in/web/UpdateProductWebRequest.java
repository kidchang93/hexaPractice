package com.example.hexapractice.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 제품 수정 Web 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductWebRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
}