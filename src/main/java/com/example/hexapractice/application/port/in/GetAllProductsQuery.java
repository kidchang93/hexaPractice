package com.example.hexapractice.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsQuery {
    private Integer page = 0;
    private Integer size = 20;
}
