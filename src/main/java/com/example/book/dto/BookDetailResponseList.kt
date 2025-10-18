package com.example.book.dto

data class BookDetailResponseList @JvmOverloads constructor(
    var id: Long,
    var title: String,
    var author: String,
    var price: Double
)