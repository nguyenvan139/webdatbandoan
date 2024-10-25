package com.example.demo.controller;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String viewRevenue(Model model) {
        Map<String, Double> weeklyRevenue = orderService.calculateRevenue(LocalDate.now().minusWeeks(1), LocalDate.now());
        Map<String, Double> monthlyRevenue = orderService.calculateRevenue(LocalDate.now().minusMonths(1), LocalDate.now());
        Map<String, Double> yearlyRevenue = orderService.calculateRevenue(LocalDate.now().minusYears(1), LocalDate.now());

        model.addAttribute("weeklyRevenue", weeklyRevenue);
        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("yearlyRevenue", yearlyRevenue);

        return "products/revenue";
    }
}
