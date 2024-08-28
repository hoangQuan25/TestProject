package com.example.bookstore.controller.admin;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.BookCategory;
import com.example.bookstore.entity.Category;
import com.example.bookstore.service.BookCategoriesService;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.CategoriesService;
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

    @Autowired
    private BookCategoriesService bookCategoriesService;

    @Autowired
    private CategoriesService categoriesService;

    // Inside your TestAddBookController

    @GetMapping("/books/new")
    public String showAddBookForm(Model model) {
        List<Category> categories = categoriesService.findAll(); // Fetch all categories from the service
        model.addAttribute("categories", categories);
        return "admin/addbook";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("price") double price,
                          @RequestParam("rating") double rating,
                          @RequestParam("stock_quantity") int stockQuantity,
                          @RequestParam("image") MultipartFile imageFile,
                          @RequestParam("categoryIds") List<Integer> categoryIds, // Add this to get selected category IDs
                          Model model) {
        try {
            // Create the new book
            Book book = new Book();
            book.setTitle(title);
            book.setPrice(price);
            book.setRating(rating);
            book.setStockQuantity(stockQuantity);

            // Encode and set the image
            String imageBase64 = encodeFileToBase64(imageFile);
            book.setImage(imageBase64);

            // Save the book first
            booksService.save(book);

            // Find selected categories and create BookCategory entries
            for (Integer categoryId : categoryIds) {
                Category category = categoriesService.findById(categoryId); // Fetch each selected category
                if (category != null) {
                    BookCategory bookCategory = new BookCategory(book, category);
                    bookCategoriesService.save(bookCategory); // Save the association
                }
            }

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
