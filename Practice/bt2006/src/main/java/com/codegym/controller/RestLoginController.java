package com.codegym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class RestLoginController {
    @GetMapping
    public ModelAndView showFormIndex() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }
}
