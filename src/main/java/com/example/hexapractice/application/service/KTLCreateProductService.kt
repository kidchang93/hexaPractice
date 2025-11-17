package com.example.hexapractice.application.service

import com.example.hexapractice.application.port.`in`.KTLCreateProductCommand
import com.example.hexapractice.application.port.out.KTLSaveProductPort
import com.example.hexapractice.common.utils.UseCase
import com.example.hexapractice.domain.KTLProduct
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class KTLCreateProductService(
    private val saveKTLProduct: KTLSaveProductPort
): UseCase<KTLCreateProductCommand, KTLProduct>{

    override fun run(@Valid request: KTLCreateProductCommand):KTLProduct{

        // 도메인 객체 생성
        val product = KTLProduct(
            id = 0L,
            name = request.name,
            description = request.description,
            price = request.price,
            stock = request.stock,
        )

        // 저장
        return saveKTLProduct.save(product)
    }
}