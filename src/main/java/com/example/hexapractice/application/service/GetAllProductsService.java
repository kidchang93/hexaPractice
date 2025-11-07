package com.example.hexapractice.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexapractice.application.port.in.GetAllProductsQuery;
import com.example.hexapractice.application.port.out.LoadProductPort;
import com.example.hexapractice.common.utils.UseCase;
import com.example.hexapractice.domain.Product;

import lombok.RequiredArgsConstructor;

/**
 * 제품 전체 조회 UseCase 구현
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllProductsService implements UseCase<GetAllProductsQuery, List<Product>> {

    private final LoadProductPort loadProductPort;

    
    @Override
    public List<Product> run(GetAllProductsQuery request) {
        // 단순 예시 - 실제로는 페이징 처리 필요
        return loadProductPort.loadAll();
    }

}
