package com.example.book.dto

data class BookUpdatedRequest @JvmOverloads constructor(
    var title: String?,
    var author: String?,
    var price: Double?
)
