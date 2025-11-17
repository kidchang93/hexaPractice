package com.example.hexapractice.adapter.out.persistence

import com.example.hexapractice.application.port.out.KTLDeleteProductPort
import com.example.hexapractice.application.port.out.KTLLoadProductPort
import com.example.hexapractice.application.port.out.KTLSaveProductPort
import com.example.hexapractice.domain.KTLProduct
import java.util.Optional

/**
 * 구현체 작성
 */
class KTLProductPersistenceAdapter(
    private val ktlProductJpaRepository: KTLProductJpaRepository,
    private val ktlProductMapper: KTLProductMapper,
) : KTLLoadProductPort, KTLSaveProductPort, KTLDeleteProductPort {


    override fun loadById(id: Long): Optional<KTLProduct> =
        ktlProductJpaRepository.findById(id)
            .map(ktlProductMapper::toDomain)

    override fun loadAll(): List<KTLProduct> {
        TODO("Not yet implemented")
    }

    override fun search(name: String): List<KTLProduct> {
        TODO("Not yet implemented")
    }

    override fun save(product: KTLProduct): KTLProduct {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: Long) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

}