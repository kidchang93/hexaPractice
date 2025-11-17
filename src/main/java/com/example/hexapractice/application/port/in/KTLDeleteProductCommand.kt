package com.example.hexapractice.application.port.`in`

import org.jetbrains.annotations.NotNull

data class KTLDeleteProductCommand (
    @field:NotNull("제품 ID 는 필수입니다.")
    val id: Long,

)