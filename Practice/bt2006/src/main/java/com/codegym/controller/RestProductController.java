package com.codegym.controller;

import com.codegym.exception.DuplicateNameExceptionCategory;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class RestProductController {
    @Autowired
    public IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) throws DuplicateNameExceptionCategory {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = (List<Product>) productService.findALl();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
       try {
           Optional<Product> productOptional = productService.findById(id);
           if (!productOptional.isPresent()) {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping
    public ResponseEntity<Product> savePro(@RequestBody Product product) throws DuplicateNameExceptionCategory {
        try {
            Optional<Product> optionalProduct = productService.findById(product.getId());
            if (!optionalProduct.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productService.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deletePro(@PathVariable Long id) {
        try {
            Optional<Product> productOptional = productService.findById(id);
            if (!productOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> findByCate(@PathVariable Long id) {
        try {
            Optional<Category> category = categoryService.findById(id);
            if (!category.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<Product> products = (List<Product>) productService.findAllByCategory(category.get());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
