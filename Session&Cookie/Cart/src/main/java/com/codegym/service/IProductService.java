package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);
}
