package com.example.hexapractice.adapter.`in`.web

import com.example.hexapractice.application.port.`in`.KTLCreateProductCommand
import com.example.hexapractice.application.port.`in`.KTLGetAllProductsQuery
import com.example.hexapractice.application.port.`in`.KTLGetProductQuery
import com.example.hexapractice.application.service.GetKTLProductService
import com.example.hexapractice.application.service.KTLCreateProductService
import com.example.hexapractice.application.service.KTLGetAllProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/v1/api/products")
class KTLProductController(
    private val ktlCreateProductService: KTLCreateProductService,
    private val ktlGetProductService: GetKTLProductService,
    private val ktlGetAllProductService: KTLGetAllProductService,
){

    /**
     * 제품 생성
     */
    @PostMapping("/create")
    fun createKTLProduct(@RequestBody request: KTLProductRequest):
            ResponseEntity<KTLProductResponse> =
        KTLCreateProductCommand(
            name = request.name,
            description = request.description,
            price = request.price,
            stock = request.stock,
        )
            .let { ktlCreateProductService.run(it) }
            .let { product -> KTLProductResponse.from(product) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/{id}")
    fun getKTLProduct(@PathVariable id: Long): ResponseEntity<KTLProductResponse> =
        KTLGetProductQuery(id)
            .let { ktlGetProductService.run(it) }
            .let { KTLProductResponse.from(it) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }

    @GetMapping("/all")
    fun getAllKTLProduct(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
    ): ResponseEntity<List<KTLProductResponse>> =
        KTLGetAllProductsQuery(page, size)
            .let { ktlGetAllProductService.run(it) }
            .let { KTLProductResponse.listFrom(it) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }



}