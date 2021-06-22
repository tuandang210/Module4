package com.codegym;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    String list[][] = {{"hello","sorry"}, {"xin chao", "xin loi"}};

    @GetMapping("/trans")
    public String Translate(@RequestParam(required = false) String word, Model model) {
        String result = null;
        for (int i = 0;i<2;i++ ) {
            for (int j=0; j<2;j++){
                if(list[0][j].equals(word)){
                    result = list[1][j];
                    break;
                }else {
                    result = "khong tim thay";
                }
            }
        }
        model.addAttribute("result", result);
        return "view";
    }
}
