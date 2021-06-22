package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import com.codegym.cms.service.ICustomerService;
import com.codegym.cms.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class ProvinceController {
    @Autowired
    public IProvinceService provinceService;

    @Autowired
    public ICustomerService customerService;

    @GetMapping("/provinces")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/province/list", "provinces", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView createProvide(){
        ModelAndView modelAndView = new ModelAndView("/province/create", "province", new Province());
        return modelAndView;
    }
    @PostMapping("/save-province")
    public ModelAndView saveProvince(@ModelAttribute Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/create", "province", new Province());
        modelAndView.addObject("message", "Success!!");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/province/create", "province", province);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        provinceService.remove(id);
        return "redirect:/provinces";
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable Long id){
        Optional<Province> provinceOptional = provinceService.findById(id);
        if(!provinceOptional.isPresent()){
            return new ModelAndView("error-404");
        }
        Iterable<Customer> customers = customerService.findAllByProvince(provinceOptional.get());
        ModelAndView modelAndView = new ModelAndView("/province/view", "province", provinceOptional.get());
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
