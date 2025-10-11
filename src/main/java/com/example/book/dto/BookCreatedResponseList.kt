package com.example.book.dto

class BookCreatedResponseList @JvmOverloads constructor(
    var id: Long,
    var title: String,
    var author: String,
    var price: Double
)