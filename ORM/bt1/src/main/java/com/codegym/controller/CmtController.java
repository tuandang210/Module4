package com.codegym.controller;

import com.codegym.model.Cmt;
import com.codegym.service.ICmtService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CmtController {
    @Autowired
    private ICmtService cmtService;

    @GetMapping
    public ModelAndView showView() {
        ModelAndView modelAndView = new ModelAndView("/view");
        List<Cmt> cmts = cmtService.findAll();
        modelAndView.addObject("cmt", new Cmt());
        modelAndView.addObject("cmts", cmts);
        return modelAndView;
    }


    @PostMapping
    public ModelAndView create(@ModelAttribute(name = "cmt") Cmt cmt){
        cmtService.save(cmt);
        ModelAndView modelAndView = new ModelAndView("redirect:/comments");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String likeComment(@PathVariable Long id){
        Cmt ab = cmtService.findOne(id);
        ab.setLike(ab.getLike()+1);
        cmtService.save(ab);
        return "redirect:/comments";
    }
}
