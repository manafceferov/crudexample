//package com.example.book.service;
//
//import com.example.book.dto.BookCreatedResponse;
//import com.example.book.dto.BookInsertRequest;
//import com.example.book.entity.Book;
//import com.example.book.mapper.BookMapper;
//import com.example.book.repository.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @Mock
//    private BookMapper bookMapper;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Test
//    void create_ShouldReturnCreatedResponse_WhenValidRequest() {
//        // Arrange
//        BookInsertRequest request = new BookInsertRequest(
//                "Test Title",
//                "Test Author",
//                20.0
//        );
//
//        Book bookEntity = new Book(
//                0L,
//                "Test Title",
//                "Test Author",
//                20.0
//        );
//
//        Book savedBook = new Book(
//                1L,
//                "Test Title",
//                "Test Author",
//                20.0
//        );
//
//        BookCreatedResponse response = new BookCreatedResponse(
//                1L,
//                "Test Title",
//                "Test Author",
//                20.0
//        );
//
//        when(bookMapper.toEntity(request)).thenReturn(bookEntity);
//        when(bookRepository.save(bookEntity)).thenReturn(savedBook);
//        when(bookMapper.toCreatedResponse(savedBook)).thenReturn(response);
//
//        // Act
//        BookCreatedResponse result = bookService.create(request);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("Test Title", result.getTitle());
//        verify(bookMapper).toEntity(request);
//        verify(bookRepository).save(bookEntity);
//        verify(bookMapper).toCreatedResponse(savedBook);
//    }
//}
