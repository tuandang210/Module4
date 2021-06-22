package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/create-customer")
    public ModelAndView showCreatForm(){
        ModelAndView modelAndView = new ModelAndView("/create", "customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customers")
    public ModelAndView create(@ModelAttribute(name = "customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("/create", "customer", customer);
        customerService.insertWithStoredProcedure(customer);
        modelAndView.addObject("message", "Yeah Yeah!");
        return modelAndView;
    }
}
