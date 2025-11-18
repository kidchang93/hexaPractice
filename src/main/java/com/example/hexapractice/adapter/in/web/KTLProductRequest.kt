package com.example.hexapractice.adapter.`in`.web

import java.math.BigDecimal

data class KTLProductRequest(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stock: Int,
)
