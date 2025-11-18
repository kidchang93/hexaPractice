package com.example.hexapractice.application.service

import com.example.hexapractice.adapter.`in`.web.KTLProductRequest
import com.example.hexapractice.application.port.`in`.KTLGetAllProductsQuery
import com.example.hexapractice.application.port.out.KTLLoadProductPort
import com.example.hexapractice.common.utils.UseCase
import com.example.hexapractice.domain.KTLProduct
import com.sun.media.sound.ModelByteBuffer.loadAll
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
open class KTLGetAllProductService(
    private val ktlLoadProductPort: KTLLoadProductPort
): UseCase<KTLGetAllProductsQuery, List<KTLProduct>>{

    override fun run(@Valid request: KTLGetAllProductsQuery): List<KTLProduct> =
        PageRequest.of(request.page, request.size)
            .let { ktlLoadProductPort.loadAll(it) }

}