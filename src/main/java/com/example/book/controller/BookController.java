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
    public String getAll(@RequestParam(required = false) String searchText, Model model) {
        model.addAttribute("books", bookService.getAll(searchText));
        model.addAttribute("bookInsertRequest", new BookInsertRequest());
        return "detail";
    }

    @GetMapping("/{id}")
    public BookDetailResponse getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public String create(@ModelAttribute BookInsertRequest request, RedirectAttributes redirectAttributes) {
        bookService.create(request);
        redirectAttributes.addFlashAttribute("successMessage", "Kitab əlavə olundu");
        return "redirect:/home/detail";
    }

    @PostMapping("/batch")
    public List<BookCreatedResponseList> createBatch(@RequestBody List<BookInsertRequestList> requests) {
        return bookService.createList(requests);
    }

    @PutMapping("/{id}")
    public BookDetailResponse update(@PathVariable Long id,
                                     @RequestBody BookUpdatedRequest request
    ) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Kitab silindi");
        return "redirect:/home/detail";
    }
}