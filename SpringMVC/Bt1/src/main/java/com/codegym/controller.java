package com.codegym;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller {
    @GetMapping("/cal")
    public String Calculator(@RequestParam(required = false) String n1, Model model) {
        double number1 = Double.parseDouble(n1);
        double n2 = number1* 23000;
        model.addAttribute("n2", n2);
        return "list";
    }
}
