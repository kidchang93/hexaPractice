package com.example.hexapractice.adapter.in.web;

import com.example.hexapractice.application.port.in.*;
import com.example.hexapractice.application.service.*;
import com.example.hexapractice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 입력 어댑터 - REST Controller
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final GetAllProductsService getAllProductsService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;
    
    /**
     * 제품 생성
     * POST /api/products
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody CreateProductWebRequest request) {
        
        // Web Request → Command 변환
        CreateProductCommand command = CreateProductCommand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        
        // UseCase 실행 (자동 검증)
        Product product = createProductService.run(command);
        
        // Domain → Response 변환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductResponse.from(product));
    }
    
    /**
     * 제품 단건 조회
     * GET /api/products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        GetProductQuery query = new GetProductQuery(id);
        Product product = getProductService.run(query);
        return ResponseEntity.ok(ProductResponse.from(product));
    }
    
    /**
     * 제품 전체 조회
     * GET /api/products
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {
        
        GetAllProductsQuery query = new GetAllProductsQuery(page, size);
        List<Product> products = getAllProductsService.run(query);
        
        List<ProductResponse> responses = products.stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    /**
     * 제품 수정
     * PUT /api/products/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductWebRequest request) {
        
        UpdateProductCommand command = UpdateProductCommand.builder()
                .id(id)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        
        Product product = updateProductService.run(command);
        return ResponseEntity.ok(ProductResponse.from(product));
    }
    
    /**
     * 제품 삭제
     * DELETE /api/products/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        DeleteProductCommand command = new DeleteProductCommand(id);
        deleteProductService.run(command);
        return ResponseEntity.noContent().build();
    }
}