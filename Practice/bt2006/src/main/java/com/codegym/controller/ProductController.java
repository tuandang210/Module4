package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findALl();
    }

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ModelAndView listProduct(@RequestParam(name = "search") Optional<String> search, @PageableDefault(size = 5) Pageable pageable) {
        Page<Product> products;
        if(search.isPresent()){
            products = productService.findAllByNameContaining(search.get(),pageable);
        }else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list", "products", products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("/product/create", "productForm", new ProductForm());
        Iterable<Category> categories = categoryService.findALl();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("productForm") ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getDescriptions(), productForm.getCategory(), fileName);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create", "productForm", new ProductForm());
        modelAndView.addObject("message", "Success!!");
        return modelAndView;
    }

    @GetMapping("/edited/{id}")
    public ModelAndView editProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/edit", "product", product.get());
            Iterable<Category> categories = categoryService.findALl();
            modelAndView.addObject("categories", categories);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("404");
            return modelAndView;
        }

    }

    @GetMapping("/deleted/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return "redirect:/products";
    }

    @GetMapping("/viewed/{id}")
    public ModelAndView viewProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/view", "product", product.get());
        return modelAndView;
    }
}