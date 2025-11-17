package com.example.hexapractice.application.port.`in`

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class KTLCreateProductCommand(

    @field:NotBlank(message = "제품명은 필수입니다.")
    @field:Size(min = 1, max = 100, message = "제품명은 1~100자 이내여야 합니다.")
    val name: String,

    @field:Size(max = 500, message = "설명은 500자 이내여야 합니다.")
    val description: String = "",

    @field:DecimalMin(value = "0.0", inclusive = true, message = "가격은 0 이상이어야 합니다.")
    val price: BigDecimal,

    @field:Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    val stock: Int

)