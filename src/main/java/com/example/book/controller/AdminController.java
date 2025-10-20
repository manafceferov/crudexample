package com.example.book.controller;

import com.example.book.service.BookService;
import com.example.book.dto.BookInsertRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;

    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String detail(Model model,
                         @RequestParam(required = false) String title
    ) {
        model.addAttribute("books", bookService.getAll(title));
        model.addAttribute("bookInsertRequest", new BookInsertRequest());
        return "detail";
    }
}
