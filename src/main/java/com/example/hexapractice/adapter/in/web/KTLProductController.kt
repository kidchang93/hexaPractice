package com.example.hexapractice.adapter.`in`.web

import com.example.hexapractice.application.port.`in`.KTLCreateProductCommand
import com.example.hexapractice.application.service.KTLCreateProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/v1/api/products")
class KTLProductController(
    private val useCase: KTLCreateProductService
){

    @PostMapping("/create")
    fun createKTLProduct(@RequestBody request: KTLProductRequest):
            ResponseEntity<KTLProductResponse> =
        KTLCreateProductCommand(
            name = request.name,
            description = request.description,
            price = request.price,
            stock = request.stock,
        )
            .let { useCase.run(it) }
            .let { product -> KTLProductResponse.from(product) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }




}