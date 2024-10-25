package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id //tạo khóa chính
    //trường tự sinh, .identity : auto,
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@column: đổi tên bảng khi khác tên trong data
    //@Transient : trường đó sẽ không được dùng để tạo bảng
    //@temporal : chỉ sử dụng khi là ngày tháng


    private String name;
    private long price;
    private String description;
    private String thumnail;

    @NotNull
    @Min(0)
    @Max(50)
    private int quantity;

    //FetchType.Lazy : chỉ lấy bảng mình dùng (gọi bảng phụ mà ko gọi bảng chính)
    //OnetoMany(Mapped=" ")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images = new ArrayList<>();


}

