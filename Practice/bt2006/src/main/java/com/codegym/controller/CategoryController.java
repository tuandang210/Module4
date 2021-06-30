package com.codegym.controller;

import com.codegym.exception.DuplicateNameExceptionCategory;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IProductService productService;

    @GetMapping("/categories")
    public ModelAndView showListCategory(@RequestParam(name = "search") Optional<String> search, @PageableDefault(size = 5) Pageable pageable) {
        Page<Category> categories;
        if (search.isPresent()) {
            categories = categoryService.findAllByNameContaining(search.get(), pageable);
            if (categories.isEmpty()) {
                ModelAndView modelAndView1 = new ModelAndView("/category/list");
                modelAndView1.addObject("message", "Not Found!!");
            }
        } else {
            categories = categoryService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/category/list", "categories", categories);
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView createCate() {
        ModelAndView modelAndView = new ModelAndView("/category/create", "category", new Category());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveCate(@Validated @ModelAttribute(name = "category") Category category, BindingResult
            bindingResult) throws DuplicateNameExceptionCategory {

        if (!bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/category/create", "category", new Category());
            categoryService.save(category);
            modelAndView.addObject("message", "Success!!");
            return modelAndView;
        } else {
            return new ModelAndView("/category/create");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCate(@PathVariable Long id) {
        try {
            ModelAndView modelAndView = new ModelAndView("/category/create", "category", categoryService.findById(id).get());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("/404");
        }
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCate(@PathVariable Long id) {
        try {
            Optional<Category> categoryOptional = categoryService.findById(id);
            if (!categoryOptional.isPresent()) {
                return new ModelAndView("/404");
            }
            Iterable<Product> products = productService.findAllByCategory(categoryOptional.get());
            ModelAndView modelAndView = new ModelAndView("/category/view", "category", categoryOptional.get());
            modelAndView.addObject("products", products);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("/404");
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCate(@PathVariable Long id) {
        categoryService.remove(id);
        return "redirect:/categories";
    }

    @ExceptionHandler(DuplicateNameExceptionCategory.class)
    public ModelAndView ShowException11() {
        ModelAndView modelAndView1 = new ModelAndView("/category/create");
        modelAndView1.addObject("message", "Duplicate name!!");
        return modelAndView1;
    }
}
