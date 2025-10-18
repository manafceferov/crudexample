package com.example.book.repository;

import com.example.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = """
            select
                *
            from books b
            where (:searchText is null or lower(b.title) like lower(concat('%', :searchText, '%'))) 
            or (:searchText is null or lower(b.author) like lower(concat('%', :searchText, '%')))
            """, nativeQuery = true
    )
    List<Book> findByTitleContainingIgnoreCase(String searchText);
}
