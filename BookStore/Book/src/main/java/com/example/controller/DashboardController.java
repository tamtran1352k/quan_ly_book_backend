package com.example.controller;

import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import com.example.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/dashboard")
    public String springMVC(ModelMap modelMap) {
        int bookNumber = bookRepository.countBook();
        int userNumber = userRepository.countUser();
        modelMap.addAttribute("book", bookNumber);
        modelMap.addAttribute("user", userNumber);
        return "/dashboard/index";
    }
}
