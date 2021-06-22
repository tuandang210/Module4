package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();

    Product findById(int id);

    Product create(Product product);

    Product edit(int id, Product product);

    void delete(int id);

    List<Product> findByName(String name);
}
