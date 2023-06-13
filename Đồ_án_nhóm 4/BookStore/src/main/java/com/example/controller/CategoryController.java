package com.example.controller;

import com.example.model.Category;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;


@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCategory(Model model, @ModelAttribute("message") final String message) {
        List<Category> categoryList = categoryService.getAll(); // Assuming Product is the class representing a product
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("message", message);
        return "/category/category";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model) {

        // create student object to hold student form data
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/create_category";

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(@ModelAttribute("category") Category category) {
        categoryService.add(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "/category/edit_category";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer id,
                         @ModelAttribute("category") Category category,
                         Model model) {


        // save updated object
        categoryService.update(id, category);
        return "redirect:/admin/category";
    }

    //handler method to handle delete request

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id, RedirectAttributesModelMap redirectAttributesModelMap) {
        Boolean isDelete = categoryService.delete(id);
        if (isDelete) {
            return "redirect:/admin/category";
        } else {
            List<Category> categoryList = categoryService.getAll(); // Assuming Product is the class representing a product
            redirectAttributesModelMap.addAttribute("categoryList", categoryList);
            redirectAttributesModelMap.addAttribute("message", "Không thể xóa, do đang sử dụng.");
            return "redirect:/admin/category";
        }
    }

}
