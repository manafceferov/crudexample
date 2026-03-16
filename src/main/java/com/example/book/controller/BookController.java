package com.example.book.controller;

import com.example.book.dto.*;
import com.example.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAll(@RequestParam(required = false) String searchText,
                         Model model
    ) {
        model.addAttribute("books", bookService.getAll(searchText));
        model.addAttribute("bookInsertRequest", new BookInsertRequest());
        return "detail";
    }

    @GetMapping("/{id}")
    public BookDetailResponse getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public String create(@ModelAttribute BookInsertRequest request,
                         Model model
    ) {
        bookService.create(request);
        model.addAttribute("successMessage", "Əlavə olundu");
        model.addAttribute("books", bookService.getAll(null));
        model.addAttribute("bookInsertRequest", new BookInsertRequest());
        return "detail";
    }

    @PostMapping("/batch")
    public List<BookCreatedResponseList> createBatch(@RequestBody List<BookInsertRequestList> requests) {
        return bookService.createBatch(requests);
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String author,
                         @RequestParam Double price,
                         RedirectAttributes redirectAttributes
    ) {
        BookUpdatedRequest request = new BookUpdatedRequest(title, author, price);
        bookService.update(id, request);
        redirectAttributes.addFlashAttribute("successMessage", "Yeniləndi");
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes
    ) {
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Silindi");
        return "redirect:/books";
    }
}