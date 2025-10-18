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
    @Column(name = "id", updatable = false, nullable = false)
    open var id: Long? = null,

    @Column(name = "title", nullable = false)
    open var title: String,

    @Column(name = "author", nullable = false)
    open var author: String,

    @Column(name = "price", nullable = false)
    open var price: Double
)