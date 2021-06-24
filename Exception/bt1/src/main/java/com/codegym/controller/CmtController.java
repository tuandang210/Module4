package com.codegym.controller;

import com.codegym.model.Cmt;
import com.codegym.service.ICmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CmtController {
    @Autowired
    private ICmtService cmtService;

    @GetMapping
    public ModelAndView showView(    @PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/view");
        Page<Cmt> comments = cmtService.findAll(pageable);
        modelAndView.addObject("cmt", new Cmt());
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }


    @PostMapping
    public ModelAndView create(@Validated @ModelAttribute(name = "cmt") Cmt cmt, BindingResult bindingResult, @PageableDefault(size = 5) Pageable pageable, RedirectAttributes rm) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/view");
            Page<Cmt> comments = cmtService.findAll(pageable);
            modelAndView.addObject("comments", comments);
            return modelAndView;
        }
        cmt.setDate(new Date());
        cmtService.save(cmt);
        return new ModelAndView("redirect:/comments");
    }

    @GetMapping("/{id}/{check}")
    public String likeComment(@PathVariable Long id, @PathVariable String check) throws Exception {
        Cmt ab = cmtService.findById(id).get();
        if (check.equals("1")) {
            ab.setLike(ab.getLike() + 1);
            cmtService.save(ab);
        } else if (ab.getLike() > 0) {
            ab.setLike(ab.getLike() - 1);
            cmtService.save(ab);
        }
        return "redirect:/comments";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) throws Exception {
        Optional<Cmt> cmt = cmtService.findById(id);
        if(!cmt.isPresent()){
            throw new Exception("Can't Do This");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/comments");
        cmtService.remove(cmt.get());
        return modelAndView;
    }
}
