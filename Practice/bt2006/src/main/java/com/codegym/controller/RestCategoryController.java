package com.codegym.controller;

import com.codegym.exception.DuplicateNameExceptionCategory;
import com.codegym.model.Category;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class RestCategoryController {
    @Autowired
    public ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        try {
            ;
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
        } catch (DuplicateNameExceptionCategory e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> save(@RequestBody Category category, @PathVariable("id") Long id) throws Exception {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(categoryOptional.get().getId());
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        try {
            Optional<Category> categoryOptional = categoryService.findById(id);
            if (!categoryOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Category>> findByName(@RequestParam(required = false) String search, Pageable pageable) {
        Page<Category> categories;
        if (search==null){
            search = "";
        }
        if (!search.isEmpty()) {
            categories = categoryService.findAllByNameContaining(search, pageable);
        } else {
            categories = categoryService.findAll(pageable);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping("/list")
    public ModelAndView showListCategory(@PageableDefault(size = 1000) Pageable pageable) {
        Page<Category> categories;
        categories = categoryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/category/list", "categories", categories);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCate(@PathVariable Long id) {
        try {
            Optional<Category> categoryOptional = categoryService.findById(id);
            if (!categoryOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            categoryService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
