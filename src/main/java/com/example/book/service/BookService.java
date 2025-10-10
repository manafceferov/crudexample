package com.example.book.service;

import com.example.book.dto.*;
import com.example.book.entity.Book;
import com.example.book.mapper.BookMapper;
import com.example.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository,
                       BookMapper bookMapper
    ) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookListResponse> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toListResponse)
                .collect(Collectors.toList());
    }

    public BookDetailResponse getById(Long id) {
        return bookMapper.toDetailResponse(bookRepository.findById(id).orElseThrow());
    }

    public BookCreatedResponse create(BookInsertRequest request) {
        return bookMapper.toCreatedResponse(bookRepository.save(bookMapper.toEntity(request)));
    }

    public BookDetailResponse update(Long id,
                                     BookUpdateRequest request
    ) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookMapper.updateEntity(request, book);
        return bookMapper.toDetailResponse(bookRepository.save(book));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}