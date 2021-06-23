package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IProductService productService;

    @GetMapping("/categories")
    public ModelAndView showListCategory() {
        Iterable<Category> categories = categoryService.findALl();
        ModelAndView modelAndView = new ModelAndView("/category/list", "categories", categories);
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView createCate(){
        ModelAndView modelAndView = new ModelAndView("/category/create", "category", new Category());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveCate(@ModelAttribute(name = "category") Category category){
        ModelAndView modelAndView = new ModelAndView("/category/create", "category", new Category());
        categoryService.save(category);
        modelAndView.addObject("message", "Success!!");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCate(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/category/create", "category", categoryService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCate(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ModelAndView("/404");
        }
        Iterable<Product> products = productService.findAllByCategory(categoryOptional.get());
        ModelAndView modelAndView = new ModelAndView("/category/view", "category", categoryOptional.get());
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteCate(@PathVariable Long id){
        categoryService.remove(id);
        return "redirect:/categories";
    }
}
