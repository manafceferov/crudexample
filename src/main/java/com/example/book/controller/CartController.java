package com.example.book.controller;

import com.example.book.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Authentication auth,
                           Model model
    ) {
        String email = auth.getName();
        model.addAttribute("cart", cartService.getOrCreateCart(email));
        return "cart/cart";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(
            @RequestParam Long bookId,
            @RequestParam(defaultValue = "1") int quantity,
            Authentication auth
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            cartService.addToCart(auth.getName(), bookId, quantity);
            int newCount = cartService.getCartItemCount(auth.getName());
            response.put("success", true);
            response.put("message", "Kitab səbətə əlavə edildi!");
            response.put("cartCount", newCount);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Xəta baş verdi!");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-quantity")
    public String updateQuantity(@RequestParam Long itemId,
                                 @RequestParam int quantity,
                                 Authentication auth
    ) {
        if (quantity >= 1) {
            cartService.updateQuantity(auth.getName(), itemId, quantity);
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove/{itemId}")
    public String removeItem(@PathVariable Long itemId,
                             Authentication auth
    ) {
        cartService.removeFromCart(auth.getName(), itemId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(Authentication auth) {
        cartService.clearCart(auth.getName());
        return "redirect:/cart";
    }
}