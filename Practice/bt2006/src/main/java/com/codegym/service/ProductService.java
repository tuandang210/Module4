package com.codegym.service;

import com.codegym.exception.DuplicateNameExceptionCategory;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    IProductRepository productRepository;

    @Override
    public Iterable<Product> findALl() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) throws Exception{
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) throws DuplicateNameExceptionCategory {
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateNameExceptionCategory();
        }
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContains(name, pageable);
    }
}
