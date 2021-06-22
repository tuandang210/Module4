package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("/categories")
    public ModelAndView showListCategory() {
        List<Category> categories = categoryService.findALl();
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
        ModelAndView modelAndView = new ModelAndView("/category/create", "category", categoryService.findById(id));
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCate(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/category/view", "category", categoryService.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteCate(@PathVariable Long id){
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
