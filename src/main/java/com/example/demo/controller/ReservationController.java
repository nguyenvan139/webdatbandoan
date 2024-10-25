package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // Hiển thị form đặt bàn
    @GetMapping("/reservations/add")
    public String showAddForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservations/add-reservation"; // Trả về trang form đặt bàn
    }

    // Lưu thông tin đặt bàn khi người dùng submit
    @PostMapping("/reservations/add")
    public String addReservation(@Valid Reservation reservation, BindingResult result) {
        if (result.hasErrors()) {
            return "reservations/add-reservation"; // Nếu có lỗi, quay lại form
        }
        reservationService.addReservation(reservation);
        return "redirect:/success"; // Chuyển hướng về trang thành công
    }

    // Hiển thị trang thành công
    @GetMapping("/success")
    public String showSuccessPage() {
        return "reservations/success"; // Trả về trang hiển thị thông báo thành công
    }

    // Hiển thị danh sách các đặt bàn
    @GetMapping("/reservations")
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservations/reservations-list"; // Trả về view hiển thị danh sách
    }
}
