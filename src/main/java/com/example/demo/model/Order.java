package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String customerAddress;

    private String customerPhone;

    private String customerNote;

    private String paymentMethod;

    private double pricetotal;

    private LocalDate orderDate;


    //không bắt buộc
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    //OnetoOne bảng 1-1 : thông tin cá x
    //ManytoMany @JoinTable:

}