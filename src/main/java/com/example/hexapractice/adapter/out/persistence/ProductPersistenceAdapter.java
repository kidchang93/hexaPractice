package com.example.hexapractice.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.hexapractice.application.port.out.DeleteProductPort;
import com.example.hexapractice.application.port.out.LoadProductPort;
import com.example.hexapractice.application.port.out.SaveProductPort;
import com.example.hexapractice.domain.Product;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements LoadProductPort, SaveProductPort, DeleteProductPort {
    
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;
    
    // === LoadProductPort 구현 ===
    
    @Override
    public Optional<Product> loadById(Long id) {
        return productJpaRepository.findById(id)
                .map(productMapper::toDomain);
    }
    
    @Override
    public List<Product> loadAll() {
        return productJpaRepository.findAll().stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Product> searchByName(String name) {
        return productJpaRepository.findByNameContaining(name).stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    // === SaveProductPort 구현 ===
    
    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = productMapper.toEntity(product);
        ProductJpaEntity savedEntity = productJpaRepository.save(entity);
        return productMapper.toDomain(savedEntity);
    }
    
    // === DeleteProductPort 구현 ===
    
    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return productJpaRepository.existsById(id);
    }
}
