package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    IBlogService blogService;

    @GetMapping("/create-blog")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("/create", "blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/creates")
    public ModelAndView save(@ModelAttribute(name = "blog") Blog blog){
        ModelAndView modelAndView = new ModelAndView("/create", "blog", new Blog());
        blogService.save(blog);
        modelAndView.addObject("message", "Success!");
        return modelAndView;
    }

    @GetMapping("/blogs")
    public ModelAndView showList(){
        List<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list", "blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/create", "blog", blogService.findById(id));
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        blogService.delete(id);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/create", "blog", blogService.findById(id));
        return modelAndView;
    }
}
