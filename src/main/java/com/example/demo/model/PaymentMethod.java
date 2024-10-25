package com.example.demo.model;

public enum PaymentMethod {
    COD("Thanh toán khi nhận hàng"),
    VNPAY("Thanh toán bằng VN-PAY");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
