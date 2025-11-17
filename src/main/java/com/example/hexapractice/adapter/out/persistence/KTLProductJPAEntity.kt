package com.example.hexapractice.adapter.out.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

/**
 * 코틀린 + JPA 에서는 기본 생성자가 필요하므로
 * var 와 기본값을 사용하거나
 * @NoArgsConstructor 대신 디폴트 값을 넣습니다.
 * - 추가적으로 JPA Entity 는 DB 와 맞닿아있으니 최대한 JPA 요구에 맞는 행위를 해라
 */
@Entity
@Table(name = "products")
class KTLProductJPAEntity(
    /**
     * id를 value 로 선언하면 불변객체이므로 JPA 에서 에러남
     * variable 로 선언해야 올바르게 작동함.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(length = 500)
    val description: String? = "",

    @Column(nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(nullable = false)
    val stock: Int,
){
    /**
     * 코틀린 스타일로 jpa entity 작성
     *
     */
    constructor(): this(
        name = "",
        price = BigDecimal.ZERO,
        stock = 0
    )

}