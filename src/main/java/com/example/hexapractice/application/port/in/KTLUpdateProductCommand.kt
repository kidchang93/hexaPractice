package com.example.hexapractice.application.port.`in`

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class KTLUpdateProductCommand(

    @field:NotNull("제품 ID는 필수입니다.")
    val id: Long,

    @field:Size(min = 1, max = 100, message = "제품명은 1~100자 이내여야 합니다.")
    val name: String,

    @field:Size(max = 500, message = "설명은 500자 이내여야 합니다.")
    val description: String,

    @field:DecimalMin(value = "0.0", inclusive = true, message = "가격은 0 이상이어야 합니다.")
    val price: BigDecimal,

    @field:Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    val stock: Int
)
