package com.example.hexapractice.application.port.out

import com.example.hexapractice.domain.KTLProduct

interface KTLSaveProductPort{
    fun save(product: KTLProduct): KTLProduct
}