package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ModelAndView showList() {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/products/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("products/save")
    public ModelAndView save(@ModelAttribute(name = "product") Product product) {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        productService.create(product);
        modelAndView.addObject("message", "Success!");
        return modelAndView;
    }

    @GetMapping("products/{id}/edit")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @PostMapping("products/update")
    public ModelAndView update(Product product){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", productService.edit(product.getId(), product));
        modelAndView.addObject("message", "Done!!");
        return modelAndView;
    }

    @GetMapping("products/{id}/delete")
    public String delete(@PathVariable int id){
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("products/{id}/view")
    public ModelAndView view(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @GetMapping("/products/search")
    public ModelAndView search(@RequestParam String name){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", productService.findByName(name));
        return modelAndView;
    }
}

