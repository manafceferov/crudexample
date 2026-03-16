package com.example.book.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var fullName: String,

    @Column(nullable = false)
    var role: String = "USER",

    @Column(nullable = false)
    var active: Boolean = true
)