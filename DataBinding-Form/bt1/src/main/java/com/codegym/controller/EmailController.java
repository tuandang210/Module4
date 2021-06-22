package com.codegym.controller;

import com.codegym.model.Email;
import com.codegym.service.EmailService;
import com.codegym.service.IEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmailController {

    IEmailService emailService = new EmailService();
    Email email1 = new  Email(1,"Vietnamese",25,true,"Hello world!");

    @GetMapping("/emails")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/view","email",email1);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute(name = "email") Email newEmail){
        email1 = newEmail;
        ModelAndView modelAndView = new ModelAndView("/view", "email", email1);
        return modelAndView;
    }
}
