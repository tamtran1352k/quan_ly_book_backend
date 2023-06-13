package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class AuthController {
//    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
//    public String index(@RequestParam(value = "error", defaultValue = "", required = false) String error, Model model) {
//        if (!error.isEmpty()) {
//            model.addAttribute("message", "Email hoặc mật khẩu không chính xác!");
//        }
//        return "auth/index";
//    }

    @RequestMapping(value = "/home/login", method = RequestMethod.GET)
    public String home(@RequestParam(value = "error", defaultValue = "", required = false) String error, Model model) {
        if (!error.isEmpty()) {
            model.addAttribute("message", "Email hoặc mật khẩu không chính xác!");
        }
        return "home/login";
    }
}
