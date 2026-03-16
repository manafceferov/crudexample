package com.example.book.mapper;

import com.example.book.dto.*;
import com.example.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book toEntity(BookInsertRequest request);

    @Mapping(target = "id", ignore = true)
    Book toEntity(BookInsertRequestList request);

    BookDetailResponse toDetailResponse(Book book);

    BookDetailResponseList toListResponse(Book book);

    BookCreatedResponse toCreatedResponse(Book book);

    BookCreatedResponseList toCreatedListResponse(Book book);

    @Mapping(target = "id", ignore = true)
    void updateEntity(BookUpdatedRequest request, @MappingTarget Book book);
}
