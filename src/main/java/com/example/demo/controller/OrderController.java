package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.model.Order;

import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    public static Order order = new Order();
    @GetMapping("/checkout")
    public String checkout() {
        return "cart/checkout";
    }

    @PostMapping("/submit")
    public String submitOrder(@RequestParam("customerName") String customerName,
                              @RequestParam("customerAddress") String customerAddress,
                              @RequestParam("customerPhone") String customerPhone,
                              @RequestParam("customerNote") String customerNote,
                              @RequestParam("paymentMethod") String paymentMethod)
    {
        List<CartItem> cartItems = cartService.getCartItems().values().stream().collect(Collectors.toList());
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        order.setCustomerName(customerName);
        order.setCustomerAddress(customerAddress);
        order.setCustomerPhone(customerPhone);
        order.setCustomerNote(customerNote);
        order.setPaymentMethod(paymentMethod);
        order.setOrderDate(LocalDate.now());
        if(paymentMethod.equals("VNPAY"))
        {
            return "redirect:/payment/pay";
        }
        else{
            return "redirect:/order/confirmation";
        }

    }

    @GetMapping()
    public String viewOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        double totalRevenue = orders.stream().mapToDouble(Order::getPricetotal).sum();
        model.addAttribute("orders", orders);
        model.addAttribute("totalRevenue", totalRevenue);
        return "orders/orders-list";
    }

    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        List<CartItem> cartItems = cartService.getCartItems().values().stream().collect(Collectors.toList());
        orderService.createOrder(order.getCustomerName(),order.getCustomerAddress(),order.getCustomerPhone(),
                order.getCustomerNote(),cartItems,order.getPaymentMethod(),order.getOrderDate());
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }
}
