package com.example.hexapractice.application.port.out

import com.example.hexapractice.domain.KTLProduct
import java.util.Optional

interface KTLLoadProductPort {

    /**
     * ID로 제품 조회
     */
    fun loadById(id: Long): Optional<KTLProduct>

    /**
     * 전체 제품 조회
     */
    fun loadAll(): List<KTLProduct>

    /**
     * 이름으로 제품 검색
     */
    fun search(name: String): List<KTLProduct>
}