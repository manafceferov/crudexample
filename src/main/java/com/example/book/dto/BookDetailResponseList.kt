package com.example.book.dto

data class BookDetailResponseList @JvmOverloads constructor(
    var id: Long? = null,
    var title: String? = null,
    var author: String? = null,
    var price: Double? = null
)