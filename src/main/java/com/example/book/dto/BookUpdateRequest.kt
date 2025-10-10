package com.example.book.dto

data class BookUpdateRequest @JvmOverloads constructor(
    var title: String?,
    var author: String?,
    var price: Double?
)
