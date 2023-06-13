package com.example.controller;

import com.example.dto.BookDto;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Category;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


@Controller
@RequestMapping("/admin/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getBooks(Model model, HttpServletRequest request) {
        List<BookDto> bookList = bookService.getAllBookList(); // Assuming Product is the class representing a product
        model.addAttribute("bookList", bookList);

        return "/book/book";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model) {

        // create student object to hold student form data
        Book bookadd = new Book();
        model.addAttribute("book", bookadd);

        List<Author> authorList = authorService.getAll();
        model.addAttribute("authorList", authorList);
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        System.out.println(bookadd);
        return "/book/create_book";

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String save(@ModelAttribute("book") Book book, @RequestParam("file") MultipartFile file) {
        String image = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                //Tạo đường dẫn lưu trữ file
                File dir = new File("src/main/resources/static/upload");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getPath() + File.separator + image);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                return "File Error:" + e.getMessage();
            }
        }
        System.out.println(image);
        book.setImage(image);
        bookService.saveBook(book);
        return "redirect:/admin/book";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Model model) {
        Book bookedit = bookService.getBookById(id);
        model.addAttribute("book", bookedit);
        List<Author> authorList = authorService.getAll();
        model.addAttribute("authorList", authorList);
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        return "/book/edit_book";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String update(@RequestParam("id") int id,
                         @ModelAttribute("book") Book book,
                         @RequestParam("file") MultipartFile file,
                         Model model) {

        String image = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                //Tạo đường dẫn lưu trữ file
                File dir = new File("src/main/resources/static/upload");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getPath() + File.separator + image);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                return "File Error:" + e.getMessage();
            }
        }
        System.out.println(image);
        book.setImage(image);
        // save updated object
        bookService.updateBook(book, id);
        return "redirect:/admin/book";
    }

    //handler method to handle delete request

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id) {
        bookService.deleteBookById(id);
        return "redirect:/admin/book";
    }


}
