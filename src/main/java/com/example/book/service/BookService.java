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

    public List<BookDetailResponseList> getAll(String title) {
        List<Book> books = title == null || title.isEmpty()
                ? bookRepository.findAll()
                : bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream()
                .map(bookMapper::toListResponse)
                .collect(Collectors.toList());
    }

    public BookDetailResponse getById(Long id) {
        return bookMapper.toDetailResponse(bookRepository.findById(id).orElseThrow());
    }

    public BookCreatedResponse create(BookInsertRequest request) {
        return bookMapper.toCreatedResponse(bookRepository.save(bookMapper.toEntity(request)));
    }

    public List<BookCreatedResponseList> createList(List<BookInsertRequestList> requests) {
        List<Book> books = requests.stream()
                .map(bookMapper::toEntity)
                .toList();

        List<Book> savedBooks = bookRepository.saveAll(books);

        return savedBooks.stream()
                .map(bookMapper::toCreatedListResponse)
                .toList();
    }

    public BookDetailResponse update(Long id,
                                     BookUpdatedRequest request
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