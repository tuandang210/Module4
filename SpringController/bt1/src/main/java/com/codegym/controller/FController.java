package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FController {
    @GetMapping("/")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiment, Model model) {
        if (condiment == null) {
            model.addAttribute("message", "Choose at least one");
            return "index";
        } else {
            model.addAttribute("condiments", condiment);
            return "list";
        }
    }
}
