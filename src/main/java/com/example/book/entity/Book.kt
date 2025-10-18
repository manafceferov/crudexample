package com.example.book.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "author", nullable = false)
    var author: String,

    @Column(name = "price", nullable = false)
    var price: Double
)