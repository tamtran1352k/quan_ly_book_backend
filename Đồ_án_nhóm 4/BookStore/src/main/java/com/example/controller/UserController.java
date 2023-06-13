package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<User> userList = userService.getAll(); // Assuming Product is the class representing a product
        model.addAttribute("userList", userList);
        return "/user/user";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model) {

        // create student object to hold student form data
        User userAdd = new User();
        model.addAttribute("user", userAdd);

        List<Role> roleList = roleService.getAll();
        model.addAttribute("roleList", roleList);
        System.out.println(userAdd);
        return "/user/create_user";

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user, Model model) {
        User add = userService.add(user);
        if (add == null) {
            model.addAttribute("message", "Tên email đã tồn tại.");
            model.addAttribute("user", user);
            List<Role> roleList = roleService.getAll();
            model.addAttribute("roleList", roleList);
            return "user/create_user";
        }
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);

        List<Role> roleList = roleService.getAll();
        model.addAttribute("roleList", roleList);
        return "/user/edit_user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@RequestParam("id") int id,
                         @ModelAttribute("user") User user,
                         Model model) {

        // get from database by id
        User existing = userService.findById(id);
        existing.setAddress(user.getAddress());
        existing.setFullname(user.getFullname());
        existing.setPhone(user.getPhone());
        existing.setEmail(user.getEmail());
        existing.setBirthday(user.getBirthday());
        existing.setRoleId(user.getRoleId());

        // save updated object
        userService.update(existing);
        return "redirect:/admin/user";
    }

    //handler method to handle delete request

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/admin/user";
    }
}
