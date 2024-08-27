package com.example.bookstore.controller.admin;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class TestAddBookController {

    @Autowired
    private BooksService booksService;

    @GetMapping("/books/new")
    public String showAddBookForm(Model model) {
        return "admin/addbook";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("price") double price,
                          @RequestParam("rating") double rating,
                          @RequestParam("stock_quantity") int stockQuantity,
                          @RequestParam("image") MultipartFile imageFile,
                          Model model) {
        try {

            Book book = new Book();
            book.setTitle(title);
            book.setPrice(price);
            book.setRating(rating);
            book.setStockQuantity(stockQuantity);


            String imageBase64 = encodeFileToBase64(imageFile);
            book.setImage(imageBase64);


            booksService.save(book);

            System.out.println("added new book: " + book.toString());

            model.addAttribute("message", "Book added successfully!");
        } catch (IOException e) {
            e.printStackTrace();

            model.addAttribute("error", "An error occurred while saving the book.");
            return "error-page";
        }

        return "redirect:/homepage";
    }


    private String encodeFileToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
