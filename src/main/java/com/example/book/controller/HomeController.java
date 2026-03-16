package com.example.book.controller;

import com.example.book.service.BookService;
import com.example.book.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final BookService bookService;
    private final CartService cartService;

    public HomeController(BookService bookService,
                          CartService cartService
    ) {
        this.bookService = bookService;
        this.cartService = cartService;
    }

    @GetMapping
    public String home(@RequestParam(required = false) String searchText,
                       Authentication auth,
                       Model model
    ) {
        model.addAttribute("books", bookService.getAll(searchText));
        model.addAttribute("searchText", searchText);

        if (auth != null) {
            int cartCount = cartService.getCartItemCount(auth.getName());
            model.addAttribute("cartItemCount", cartCount);
        } else {
            model.addAttribute("cartItemCount", 0);
        }
        return "home";
    }

    @GetMapping("/{id}")
    public String bookDetail(@PathVariable Long id,
                             Authentication auth,
                             Model model
    ) {
        model.addAttribute("book", bookService.getById(id));

        if (auth != null) {
            int cartCount = cartService.getCartItemCount(auth.getName());
            model.addAttribute("cartItemCount", cartCount);
        } else {
            model.addAttribute("cartItemCount", 0);
        }
        return "book-detail";
    }
}