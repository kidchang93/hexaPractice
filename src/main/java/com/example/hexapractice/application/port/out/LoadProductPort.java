package com.example.hexapractice.application.port.out;

import java.util.List;
import java.util.Optional;

import com.example.hexapractice.domain.Product;

public interface LoadProductPort {
    /**
     * ID로 제품 조회
     */
    Optional<Product> loadById(Long id);
    
    /**
     * 전체 제품 조회
     */
    List<Product> loadAll();
    
    /**
     * 이름으로 제품 검색
     */
    List<Product> searchByName(String name);
}
