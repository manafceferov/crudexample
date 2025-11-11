package com.example.book.controller;

import com.example.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String home(@RequestParam(required = false) String searchText,
                       Model model) {
        model.addAttribute("books", bookService.getAll(searchText));
        model.addAttribute("searchText", searchText);
        return "home";
    }

}