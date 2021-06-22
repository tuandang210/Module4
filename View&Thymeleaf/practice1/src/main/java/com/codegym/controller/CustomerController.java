package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customer")
    public ModelAndView showList() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("customer/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("customer/save")
    public ModelAndView save(@ModelAttribute(name = "customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        customerService.create(customer);
        modelAndView.addObject("message", "Success!");
        return modelAndView;
    }

    @GetMapping("customer/{id}/edit")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("customer/update")
    public ModelAndView update(Customer customer){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("message", "Okay Bro!");
        modelAndView.addObject("customer", customerService.edit(customer.getId(), customer));
        return modelAndView;
    }

    @GetMapping("customer/{id}/delete")
    public String delete(@PathVariable int id){
        customerService.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("customer/{id}/view")
    public ModelAndView view(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }
}
