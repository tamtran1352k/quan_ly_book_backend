package com.example.controller;

import com.example.model.Role;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getRoles(Model model) {
        List<Role> roleList = roleService.getAll(); // Assuming Product is the class representing a product
        model.addAttribute("roleList", roleList);

        return "/role/role";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model) {

        // create student object to hold student form data
        Role roleadd = new Role();
        model.addAttribute("role", roleadd);
        System.out.println(roleadd);
        return "role/create_role";

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(@ModelAttribute("role") Role role, Model model) {
        Role add = roleService.add(role);
        if (add != null) {
            return "redirect:/admin/role";
        } else {
            model.addAttribute("message", "Tên quyền đã tồn tại.");
            return "role/create_role";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Model model) {
        Role roleedit = roleService.findById(id);
        model.addAttribute("role", roleedit);
        return "/role/edit_role";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer id,
                         @ModelAttribute("role") Role role,
                         Model model) {


        // save updated object
        roleService.update(id, role);
        return "redirect:/admin/role";
    }

    //handler method to handle delete request

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id) {
        roleService.delete(id);
        return "redirect:/admin/role";
    }

}
