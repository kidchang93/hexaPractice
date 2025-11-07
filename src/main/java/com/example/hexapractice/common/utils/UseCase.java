package com.example.hexapractice.common.utils;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UseCase<T, R> {
    R run(@Valid T Request);
}
