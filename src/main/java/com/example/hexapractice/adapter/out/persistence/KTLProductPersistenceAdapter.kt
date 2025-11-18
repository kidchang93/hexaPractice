package com.example.hexapractice.adapter.out.persistence

import com.example.hexapractice.application.port.out.KTLDeleteProductPort
import com.example.hexapractice.application.port.out.KTLLoadProductPort
import com.example.hexapractice.application.port.out.KTLSaveProductPort
import com.example.hexapractice.domain.KTLProduct
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.Optional

/**
 * 구현체 작성
 */
@Component
class KTLProductPersistenceAdapter(
    private val ktlProductJpaRepository: KTLProductJpaRepository,
    private val ktlProductMapper: KTLProductMapper,
) : KTLLoadProductPort, KTLSaveProductPort, KTLDeleteProductPort {


    /**
     * 단건 조회 쿼리
     */
    override fun loadById(id: Long): Optional<KTLProduct> =
        ktlProductJpaRepository.findById(id)
            .map(ktlProductMapper::toDomain)

    /**
     * 모든 메서드 조회 쿼리
     */
    override fun loadAll(pageable: Pageable): List<KTLProduct> =
        ktlProductJpaRepository.findAll(pageable)
            .content    // Page<KTLProductJPAEntity> 를 반환하므로 content 로 리스트를 추출한 뒤 map 을 적용해야함.
            .map(ktlProductMapper::toDomain)


    override fun search(name: String): List<KTLProduct> {
        TODO("Not yet implemented")
    }

    /**
     * 자바 스타일
     */
//    override fun save(product: KTLProduct): KTLProduct {
//        val entity = ktlProductMapper.toEntity(product)
//        val savedEntity = ktlProductJpaRepository.save(entity);
//        return ktlProductMapper.toDomain(savedEntity);
//    }
    /**
     * 저장 메서드 코틀린 스럽게 바꾸기
     */
    override fun save(product: KTLProduct): KTLProduct =
        ktlProductMapper.toEntity(product)
            .let { ktlProductJpaRepository.save(it) }
            .let { ktlProductMapper.toDomain(it) }


    override fun deleteProduct(id: Long) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

}