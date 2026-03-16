package com.example.book.service;

import com.example.book.entity.*;
import com.example.book.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       BookRepository bookRepository,
                       UserRepository userRepository
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Cart getOrCreateCart(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User tapılmadı"));
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart(null, user, new java.util.ArrayList<>(), 0.0);
                    return cartRepository.save(newCart);
                });
    }

    @Transactional
    public void addToCart(String userEmail,
                          Long bookId,
                          int quantity
    ) {
        Cart cart = getOrCreateCart(userEmail);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Kitab tapılmadı"));
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst()
                .orElse(null);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(null, cart, book, quantity, book.getPrice());
            cart.getItems().add(newItem);
        }
        updateTotalPrice(cart);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeFromCart(String userEmail,
                               Long cartItemId
    ) {
        Cart cart = getOrCreateCart(userEmail);
        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
        updateTotalPrice(cart);
        cartRepository.save(cart);
    }

    @Transactional
    public void updateQuantity(String userEmail,
                               Long itemId,
                               int newQuantity
    ) {
        Cart cart = getOrCreateCart(userEmail);
        cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(newQuantity));

        updateTotalPrice(cart);
        cartRepository.save(cart);
    }

    public int getCartItemCount(String userEmail) {
        try {
            Cart cart = getOrCreateCart(userEmail);
            return cart.getItems().stream()
                    .mapToInt(CartItem::getQuantity)
                    .sum();
        } catch (Exception e) {
            return 0;
        }
    }

    @Transactional
    public void clearCart(String userEmail) {
        Cart cart = getOrCreateCart(userEmail);
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }

    private void updateTotalPrice(Cart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(total);
    }
}