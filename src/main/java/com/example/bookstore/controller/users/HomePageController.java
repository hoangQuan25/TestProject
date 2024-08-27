package com.example.bookstore.controller.users;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private BooksService booksService;

    @GetMapping("/homepage")
    public String showHomepage(Model model) {
        List<Book> books = booksService.findAll();
        model.addAttribute("books", books);
        return "users/homepage";
    }
}
