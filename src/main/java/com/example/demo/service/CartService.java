package com.example.demo.service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final Map<Long, CartItem> cartItems = new HashMap<>();

    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough products in stock");
        }

        CartItem cartItem = cartItems.get(productId);
        if (cartItem == null) {
            cartItem = new CartItem(product, quantity);
        } else {
            int newQuantity = cartItem.getQuantity() + quantity;
            if (newQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("Not enough products in stock");
            }
            cartItem.setQuantity(newQuantity);
        }
        cartItems.put(productId, cartItem);
    }

    public void increaseQuantity(Long productId) {
        CartItem cartItem = cartItems.get(productId);
        if (cartItem != null) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            if (cartItem.getQuantity() < product.getQuantity()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else {
                throw new IllegalArgumentException("Maximum quantity reached for product: " + product.getName());
            }
        }
    }

    public void decreaseQuantity(Long productId) {
        CartItem cartItem = cartItems.get(productId);
        if (cartItem != null && cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
    }

    public void removeFromCart(Long productId) {
        cartItems.remove(productId);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public Map<Long, CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return cartItems.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
