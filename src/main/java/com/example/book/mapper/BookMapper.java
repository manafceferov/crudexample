package com.example.book.mapper;

import com.example.book.dto.*;
import com.example.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookInsertRequest request);
    Book toEntity(BookInsertRequestList request);
    BookDetailResponse toDetailResponse(Book book);
    BookListResponse toListResponse(Book book);
    BookCreatedRequest toCreatedResponse(Book book);
    BookCreatedRequestList toCreatedListResponse(Book book);
    void updateEntity(BookUpdateRequest request, @MappingTarget Book book);
}
