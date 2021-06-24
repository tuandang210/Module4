package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class UserController {

    @ModelAttribute("user")
    public User setupUserForm() {
        return new User();
    }

    @RequestMapping("/login")
    public String index(@CookieValue(value = "setupUser", defaultValue = "") String setupUser, Model model) {
        Cookie cookie = new Cookie("setupUser", setupUser);
        model.addAttribute("cookieValue", cookie);
        return "/login";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") User user, @CookieValue(value = "setupUser", defaultValue = "") String setupUser,
                          Model model, HttpServletRequest request, HttpServletResponse response) {
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("12345")) {
            if (user.getEmail() != null) {
                setupUser = user.getEmail();
            }
            // create cookie and set it in response
            Cookie cookie = new Cookie("setupUser", setupUser);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);


            //get all cookie
            Cookie[] cookies = request.getCookies();
            //iterate each cookie
            for (Cookie x : cookies) {
                //display only the cookie with the name 'setupUser'
                if (x.getName().equals("setupUser")) {
                    model.addAttribute("cookieValue", x);
                    break;
                } else {
                    x.setValue("");
                    model.addAttribute("cookieValue", x);
                    break;
                }
            }
            model.addAttribute("message", "Login success. Welcome ");
        } else {
            user.setEmail("");
            Cookie cookie = new Cookie("setupUser", setupUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "/login";
    }
}
