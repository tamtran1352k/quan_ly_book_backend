package com.example.controller;

import com.example.model.Author;
import com.example.service.AuthorService;
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
@RequestMapping("/admin/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAuthors(Model model, @ModelAttribute("message") final String message) {
        List<Author> authorList = authorService.getAll();
        model.addAttribute("authorList", authorList);
        model.addAttribute("message", message);

        return "/author/author";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model) {

        Author authoradd = new Author();
        model.addAttribute("author", authoradd);
        System.out.println(authoradd);
        return "/author/create_author";

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(@ModelAttribute("author") Author author) {
        authorService.add(author);
        return "redirect:/admin/author";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Model model) {
        Author authorEdit = authorService.findById(id);
        model.addAttribute("author", authorEdit);
        return "/author/edit_author";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@RequestParam("id") int id,
                         @ModelAttribute("author") Author author,
                         Model model) {

        // get from database by id
        Author existing = authorService.findById(id);
        existing.setName(author.getName());


        // save updated object
        authorService.update(existing);
        return "redirect:/admin/author";
    }

    //handler method to handle delete request

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id, RedirectAttributesModelMap redirectAttributesModelMap) {
        Boolean isDelete = authorService.delete(id);
        if (isDelete) {
            return "redirect:/admin/author";
        } else {
//            List<Author> authorList = authorService.getAll();
//            model.addAttribute("authorList", authorList);
            redirectAttributesModelMap.addAttribute("message", "Không thể xóa, do đang sử dụng.");
            return "redirect:/admin/author";
        }
    }


}
