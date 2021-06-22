package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalController {
    @GetMapping("/")
    public String index(@RequestParam(required = false, name = "operation") String operation ,@RequestParam(required = false, name = "number1") Integer number1, @RequestParam(required = false, name = "number2") Integer number2, Model model) {
        if(number1==null || number2==null) {
            model.addAttribute("message", "Chon so di ba");
            return "index";
        }
        int result;
        switch (operation){
            case "+":
                result= number1+number2;
                break;
            case "-":
                result= number1-number2;
                break;
            case "*":
                result=number1*number2;
                break;
            case "/":
                if(number2==0){
                    model.addAttribute("message", "so thu 2 khac 0 di ba oy");
                    return "index";
                }else {
                    result=number1/number2;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
        model.addAttribute("result", result);
        return "index";
    }
}
