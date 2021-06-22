package com.codegym.repository;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    public static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "C300 AMG", 5000, "Mercedes", "Daimler"));
        products.add(new Product(2, "A8", 5800, "Audi", "Volkswagen"));
        products.add(new Product(3, "M5", 4600, "BMW", "BMW"));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product create(Product product) {
        products.add(products.size(), product);
        return product;
    }

    @Override
    public Product edit(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
            }
        }
        return product;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> a = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                a.add(product);
            }
        }
        if(a.size()==0){
            return products;
        }
        return a;
    }
}
