package com.example.hexapractice.application.port.out;

import com.example.hexapractice.domain.Product;

public interface SaveProductPort {
    Product save(Product product);
}
