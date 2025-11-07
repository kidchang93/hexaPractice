package com.example.hexapractice.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexapractice.application.port.in.GetProductQuery;
import com.example.hexapractice.application.port.out.LoadProductPort;
import com.example.hexapractice.common.utils.UseCase;
import com.example.hexapractice.domain.Product;

import lombok.RequiredArgsConstructor;

/**
 * 제품 단건 조회 UseCase 구현
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetProductService implements UseCase<GetProductQuery, Product> {
    private final LoadProductPort loadProductPort;
    
    @Override
    public Product run(GetProductQuery request) {
        return loadProductPort.loadById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "제품을 찾을 수 없습니다. ID: " + request.getId()
                ));
    }
}
