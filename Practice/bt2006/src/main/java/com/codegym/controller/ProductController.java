package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:upload_file.properties")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("/products")
    public ModelAndView listProduct(){
        List<Product> products = productService.findALl();
        ModelAndView modelAndView = new ModelAndView("/product/list", "products", products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView createProduct(){
        ModelAndView modelAndView = new ModelAndView("/product/create", "productForm", new ProductForm());
        List<Category> categories = categoryService.findALl();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/save-product")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productForm){
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getDescriptions(), productForm.getCategory_id(), fileName);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create", "productForm", new ProductForm());
        modelAndView.addObject("message", "Success!!");
        return modelAndView;
    }

    @GetMapping("/edited/{id}")
    public ModelAndView editProduct(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/product/edit", "product", productService.findById(id));
        return modelAndView;
    }

    @GetMapping("/deleted/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/viewed/{id}")
    public ModelAndView viewProduct(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/product/view", "product", productService.findById(id));
        return modelAndView;
    }
}