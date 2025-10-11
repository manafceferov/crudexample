package com.example.book.controller;

import com.example.book.dto.*;
import com.example.book.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookListResponse> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDetailResponse getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookCreatedResponse create(@RequestBody BookInsertRequest request) {
        return bookService.create(request);
    }

    @PostMapping("/all")
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
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
