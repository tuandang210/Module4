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
            categoryService.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
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

    @GetMapping
    public ResponseEntity<Page<Category>> findAllCate(@PageableDefault(size = 1000) Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
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

    @GetMapping("/search")
    public ResponseEntity<Page<Category>> findByName(@RequestParam String search, Pageable pageable) {
        Page<Category> categories;
        if (!search.isEmpty()) {
            categories = categoryService.findAllByNameContaining(search, pageable);
        } else {
            categories = categoryService.findAll(pageable);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping("/list")
    public ModelAndView showListCategory(@PageableDefault(size = 5) Pageable pageable) {
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
