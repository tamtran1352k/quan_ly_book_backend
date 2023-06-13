package com.example.controller;

import com.example.dto.BookDto;
import com.example.model.User;
import com.example.service.BookService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getHome(Model model) {
        List<BookDto> bookList = bookService.getAllBookList();
        model.addAttribute("bookList", bookList);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username == "anonymousUser") {
            model.addAttribute("username", username);
        } else {
            com.example.model.User user = userService.findByEmail(username);
            model.addAttribute("username", username);
            model.addAttribute("user", user);
        }

        return "/home/index";
    }

    @RequestMapping(value = "userDetail", method = RequestMethod.GET)
    public String thongTinCaNhan(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		lấy id nhân viên
        String username = authentication.getName();
        if (username == "anonymousUser") {
            model.addAttribute("username", username);
        } else {
            User user = userService.findByEmail(username);
            model.addAttribute("username", username);
            model.addAttribute("user", user);
            model.addAttribute("users", user);
        }
        return "home/thongTinCaNhan";
    }

    @RequestMapping(value = "userDetail", method = RequestMethod.POST)
    public String thongTinCaNhan(@ModelAttribute("users") User dto, Model model) {
        //		lấy id nhân viên
        User user = userService.update(dto);
//		System.err.println(dto.getAvatar() + " " +dto.getRoleId());
        model.addAttribute("users", user);
        model.addAttribute("user", user);
        return "redirect:home";
    }
}
