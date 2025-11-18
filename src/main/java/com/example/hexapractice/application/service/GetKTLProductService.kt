package com.example.hexapractice.application.service

import com.example.hexapractice.application.port.`in`.KTLGetProductQuery
import com.example.hexapractice.application.port.out.KTLLoadProductPort
import com.example.hexapractice.common.utils.UseCase
import com.example.hexapractice.domain.KTLProduct
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
open class GetKTLProductService(
    private val ktlLoadProductPort: KTLLoadProductPort
): UseCase<KTLGetProductQuery, KTLProduct> {

    /**
     * UseCase 의 Run 메서드를 상속받아 작성됨.
     */
    override fun run(query: KTLGetProductQuery): KTLProduct =
        ktlLoadProductPort.loadById(query.id)
            .orElseThrow {
                IllegalArgumentException("제품을 찾을 수 없습니다. ID: ${query.id}")
            }
}