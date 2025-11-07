package com.example.hexapractice.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexapractice.application.port.in.DeleteProductCommand;
import com.example.hexapractice.application.port.out.DeleteProductPort;
import com.example.hexapractice.common.utils.UseCase;

import lombok.RequiredArgsConstructor;

/**
 * 제품 삭제 UseCase 구현
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DeleteProductService implements UseCase<DeleteProductCommand, Void> { 
    
    private final DeleteProductPort deleteProductPort;

    @Override
    public Void run(DeleteProductCommand request) {
        // 존재 여부 확인
        if (!deleteProductPort.existsById(request.getId())) {
            throw new IllegalArgumentException(
                "제품을 찾을 수 없습니다. ID: " + request.getId()
            );
        }
        
        // 삭제
        deleteProductPort.deleteById(request.getId());
        return null;
    }
}
