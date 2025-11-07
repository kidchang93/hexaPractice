package com.example.hexapractice.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexapractice.application.port.in.CreateProductCommand;
import com.example.hexapractice.application.port.out.SaveProductPort;
import com.example.hexapractice.common.utils.UseCase;
import com.example.hexapractice.domain.Product;

import lombok.RequiredArgsConstructor;

/**
 * 제품 생성 UseCase 구현
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CreateProductService implements UseCase<CreateProductCommand, Product> {
    private final SaveProductPort saveProductPort;
    
    @Override
    public Product run(CreateProductCommand request) {

        // 도메인 객체 생성
        Product product = Product.builder()
            .name(request.getName())
            .description(request.getDescription())
            .price(request.getPrice())
            .stock(request.getStock())
            .build();
        
            // 저장(출력 포트 사용)
        return saveProductPort.save(product);
    }
}
