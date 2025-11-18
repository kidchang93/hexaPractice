package com.example.hexapractice.application.port.`in`

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class KTLGetProductQuery(

    @field:NotNull(message = "제품 ID는 필수입니다.")
    val id: Long
)
