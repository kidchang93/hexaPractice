package com.example.hexapractice.application.port.`in`

import jakarta.validation.constraints.NotBlank

data class KTLGetProductQuery(

    @field:NotBlank(message = "제품 ID는 필수입니다.")
    val id: Long
)
