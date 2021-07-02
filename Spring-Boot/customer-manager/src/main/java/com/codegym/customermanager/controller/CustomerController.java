package com.codegym.customermanager.controller;

import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/create-customer")
    public ModelAndView showCreate() {
        return new ModelAndView("/create", "customer", new Customer());
    }

    @PostMapping("/create-customer")
    public ModelAndView save(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/create", "customer", new Customer());
        modelAndView.addObject("message", "Okla");
        customerService.save(customer);
        return modelAndView;
    }

    @GetMapping("/customers")
    public ModelAndView showList() {
        return new ModelAndView("/list", "customers", customerService.findAll());
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Optional<Customer>  customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()){
            return new ModelAndView("/404");
        }
        return new ModelAndView("/create", "customer", customerOptional.get());
    }

    @GetMapping("/delete-customer/{id}")
    public String delete(@PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()){
            return "/404";
        }
        customerService.remove(id);
        return "redirect:/customers";
    }
}
