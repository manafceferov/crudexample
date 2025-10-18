package com.example.book.controller;

import com.example.book.service.BookService;
import com.example.book.dto.BookInsertRequest;
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
    public String home(Model model) {
        model.addAttribute("books", bookService.getAll(null));
        return "home";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam(required = false) String title) {
        model.addAttribute("books", bookService.getAll(title));
        model.addAttribute("bookInsertRequest", new BookInsertRequest());
        return "detail";
    }
}