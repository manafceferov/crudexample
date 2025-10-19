package com.example.book.repository;

import com.example.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
                SELECT b
                FROM Book b
                WHERE (:searchText IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :searchText, '%')))
                   OR (:searchText IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchText, '%')))
            """)
    List<Book> findByTitleContainingIgnoreCase(String searchText);

}
