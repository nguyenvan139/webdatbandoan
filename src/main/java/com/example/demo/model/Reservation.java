package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotNull(message = "Vui lòng chọn thời gian")
    private String time;
    @NotNull(message = "Vui lòng chọn ngày")
    private LocalDate date;

    @Min(value = 1, message = "Số lượng khách phải lớn hơn 0")
    private int numberOfGuests;

    @Column(length = 500)
    private String specialRequest;

}
