package com.example.hexapractice.application.port.out

import com.example.hexapractice.domain.KTLProduct

interface KTLDeleteProductPort {
    /**
     * id 로 제품 삭제하기.
     */
    fun deleteProduct(id: Long);

    /**
     * 제품 존재 여부 확인
     */
    fun existsById(id: Long): Boolean;
}