package com.example.hexapractice.application.port.out;

public interface DeleteProductPort {
    /**
     * ID로 제품 삭제
     */
    void deleteById(Long id);
    
    /**
     * 제품 존재 여부 확인
     */
    boolean existsById(Long id);
}
