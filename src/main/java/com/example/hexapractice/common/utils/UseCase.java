package com.example.hexapractice.common.utils;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * 공통 UseCase 인터페이스
 * 
 * @param <T> 입력 타입 (Command/Query)
 * @param <R> 반환 타입 (Domain/DTO)
 */
@Validated
public interface UseCase<T, R> {
    
    /**
     * UseCase 실행
     * 
     * @param request 요청 데이터 (자동 검증됨)
     * @return 실행 결과
     */
    R run(@Valid T request);
}
