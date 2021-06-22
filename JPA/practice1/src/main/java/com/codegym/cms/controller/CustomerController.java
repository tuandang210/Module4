package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showAll() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list", "customers", customers);
        return modelAndView;
    }

    @GetMapping("/create-customers")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("/create", "customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customers")
    public ModelAndView create(@ModelAttribute(name = "customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/create", "customer", new Customer());
        customerService.save(customer);
        modelAndView.addObject("message", "Success!");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/create", "customer", customerService.findById(id));
        return modelAndView;
    }

   @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        customerService.remove(id);
        return "redirect:/customers";
   }

   @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/view", "customer", customerService.findById(id));
        return modelAndView;
   }
}
