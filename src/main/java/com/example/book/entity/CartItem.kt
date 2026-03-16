package com.example.book.entity

import jakarta.persistence.*

@Entity
@Table(name = "cart_items")
data class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    var cart: Cart,

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    var book: Book,

    @Column(nullable = false)
    var quantity: Int = 1,

    @Column(nullable = false)
    var price: Double // kitabın qiyməti o vaxt
)