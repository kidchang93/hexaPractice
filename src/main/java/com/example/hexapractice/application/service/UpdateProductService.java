package com.example.hexapractice.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexapractice.application.port.in.UpdateProductCommand;
import com.example.hexapractice.application.port.out.LoadProductPort;
import com.example.hexapractice.application.port.out.SaveProductPort;
import com.example.hexapractice.common.utils.UseCase;
import com.example.hexapractice.domain.Product;

import lombok.RequiredArgsConstructor;

/**
 * 제품 수정 UseCase 구현
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UpdateProductService implements UseCase<UpdateProductCommand, Product> {
 
    private final LoadProductPort loadProductPort;
    private final SaveProductPort saveProductPort;

    @Override
    public Product run(UpdateProductCommand request) {
        // 기존 제품 조회
        Product existingProduct = loadProductPort.loadById(request.getId())
            .orElseThrow(() -> new IllegalArgumentException(
                "제품을 찾을 수 없습니다. ID: " + request.getId()
            ));

        // 수정된 제품 생성(변경된 필드만 업데이트)
        Product updatedProduct = Product.builder()
                .id(existingProduct.getId())
                .name(request.getName() != null ? request.getName() : existingProduct.getName())
                .description(request.getDescription() != null ? request.getDescription() : existingProduct.getDescription())
                .price(request.getPrice() != null ? request.getPrice() : existingProduct.getPrice())
                .stock(request.getStock() != null ? request.getStock() : existingProduct.getStock())
                .build();

        // 제품 저장
        return saveProductPort.save(updatedProduct);
        
    }
}
