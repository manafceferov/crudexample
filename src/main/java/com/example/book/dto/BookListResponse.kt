package com.example.book.dto

data class BookListResponse @JvmOverloads constructor(
    var id: Long,
    var title: String,
    var author: String
)