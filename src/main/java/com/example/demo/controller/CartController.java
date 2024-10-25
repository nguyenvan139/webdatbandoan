package com.example.demo.controller;

import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems().values());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Model model) {
        try {
            cartService.addToCart(productId, quantity);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cartItems", cartService.getCartItems().values());
            model.addAttribute("totalPrice", cartService.getTotalPrice());
            return "cart/cart";
        }
        return "redirect:/cart";
    }

    @PostMapping("/increase/{productId}")
    public String increaseQuantity(@PathVariable Long productId, Model model) {
        try {
            cartService.increaseQuantity(productId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("cartItems", cartService.getCartItems().values());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart/cart";
    }

    @PostMapping("/decrease/{productId}")
    public String decreaseQuantity(@PathVariable Long productId, Model model) {
        cartService.decreaseQuantity(productId);
        model.addAttribute("cartItems", cartService.getCartItems().values());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart/cart";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
