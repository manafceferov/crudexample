package com.example.book.dto

data class BookUpdatedRequest @JvmOverloads constructor(
    var title: String? = null,
    var author: String? = null,
    var price: Double? = null
)
